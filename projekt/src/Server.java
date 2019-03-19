import java.io.*;
import java.net.*;
import java.util.*;
import org.json.*;

public class Server {

	protected int server_port = 4434;
	protected List<Socket> clients = new ArrayList<Socket>();
	
	public static void main(String[] args) throws Exception {
		new Server();
	}

	public Server() {
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
				ServerConnector conn = new ServerConnector(this, client_socket);
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
	
	public ServerConnector(Server server, Socket socket) {
		this.server = server;
		this.socket = socket;
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
			String response;
			
			try {
				request = in.readUTF();
				
				if (request.length() == 0) // invalid message
					continue;
				
				JSONObject res = RequestHandler.handleRequest(new JSONObject(request), new ImageProcessing());

				out.writeUTF(res.toString());
				out.flush();
				System.out.println("[server] " + res.toString());
								
			} catch (Exception e) {
				System.err.println("[system] user " + this.socket.getInetAddress().getHostName() + ":" + this.socket.getPort() + " disconnected");
				return;
			}
		}
	}
}

