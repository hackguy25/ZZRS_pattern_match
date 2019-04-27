import java.io.*;
import java.net.*;
import java.util.*;
import org.json.*;

public class Server {

    protected int server_port = 4434;
    protected List<Socket> clients = new ArrayList<Socket>();

    public static void main(String[] args) throws Exception {
        ImageLoader il = new ImageLoader();
        new Server(il);
    }

    public Server(ImageLoader il) {
        ServerSocket server_socket = null;
        Socket client_socket = null;

        try {
            server_socket = new ServerSocket(server_port);
            System.out.println("[system] Socket successfully created.");
        } catch (Exception e) {
            System.err.println("[system] could not create socket on port " + this.server_port);
            e.printStackTrace(System.err);
            System.exit(1);
        }

        try {
            while (true) {
                client_socket = server_socket.accept();

                synchronized(this) {
                    clients.add(client_socket);
                }
                ServerConnector conn = new ServerConnector(this, client_socket, il);
                conn.start();
            }
        } catch (Exception e) {
            System.err.println("[error] Accept failed.");
            e.printStackTrace(System.err);
            System.exit(1);
        }

        System.out.println("[system] closing server socket ...");
        try {
            server_socket.close();
        } catch (IOException e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }
    }

}

class ServerConnector extends Thread {
    private Server server;
    private Socket socket;
    private ImageLoader il;

    public ServerConnector(Server server, Socket socket, ImageLoader il) {
        this.server = server;
        this.socket = socket;
        this.il = il;
    }

    public void run() {
        System.out.println("[system] connected with " + this.socket.getInetAddress().getHostName() + ":" + this.socket.getPort());

        DataInputStream in;
        DataOutputStream out;

        try {
            in = new DataInputStream(this.socket.getInputStream());
            out = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            System.err.println("[system] could not open input stream!");
            e.printStackTrace(System.err);
            return;
        }

        while (true) {
            String request;

            try {
                request = in.readUTF();

                if (request.length() == 0) // invalid message
                    continue;

                JSONObject res = RequestHandler.handleRequest(new JSONObject(request), new ImageProcessing(), il);

                out.writeUTF(res.toString());
                out.flush();
                System.out.println("[server] " + res.toString());

            } catch (EOFException e) {
                System.out.println("[system] user " + this.socket.getInetAddress().getHostName() + ":" + this.socket.getPort() + " disconnected");
                System.out.println(e.toString());
                return;
            } catch (Exception e) {
                System.err.println("[system] user " + this.socket.getInetAddress().getHostName() + ":" + this.socket.getPort() + " disconnected");
                System.err.println(e.toString());
                return;
            }
        }
    }
}

