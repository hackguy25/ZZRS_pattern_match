import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

	protected int serverPort = 443;
	protected List<Socket> clients = new ArrayList<Socket>();
	
	public static void main(String[] args) throws Exception {
		new Server();
	}

	public Server() {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(serverPort);
		} catch (Exception e) {
			System.err.println("[system] could not create socket on port " + this.serverPort);
			e.printStackTrace(System.err);
			System.exit(1);
		}

		System.out.println("[system] listening ...");
		try {
			while (true) {
				Socket newClientSocket = serverSocket.accept();
					
				synchronized(this) {
					clients.add(newClientSocket);
				}
				ServerConnector conn = new ServerConnector(this, newClientSocket);
				conn.start();
			}
		} catch (Exception e) {
			System.err.println("[error] Accept failed.");
			e.printStackTrace(System.err);
			System.exit(1);
		}

		System.out.println("[system] closing server socket ...");
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}

	public void sendToAllClients(String message) throws Exception {
		Iterator<Socket> i = clients.iterator();
		while (i.hasNext()) {
			Socket socket = (Socket) i.next();
			try {
				DataOutputStream out = new DataOutputStream(socket.getOutputStream()); // create output stream for sending messages to the client
				out.writeUTF(message); // send message to the client
			} catch (Exception e) {
				System.err.println("[system] could not send message to a client");
				e.printStackTrace(System.err);
			}
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
		try {
			in = new DataInputStream(this.socket.getInputStream());
		} catch (IOException e) {
			System.err.println("[system] could not open input stream!");
			e.printStackTrace(System.err);
			return;
		}

		while (true) {
			String msg;
			
			try {
				msg = in.readUTF();
				System.out.println("[user]: "+msg);
			} catch (Exception e) {
				System.err.println("[system] there was a problem while reading message client on port " + this.socket.getPort());
				e.printStackTrace(System.err);
				return;
			}

			try {
				this.server.sendToAllClients(msg); // send message to all clients
			}catch (Exception e) {
				System.err.println("[system] there was a problem while sending the message to all clients");
				e.printStackTrace(System.err);
				continue;
			}
		}
	}
}
