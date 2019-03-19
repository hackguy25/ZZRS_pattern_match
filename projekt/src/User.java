import java.io.*;
import java.net.*;
import org.json.*;

public class User extends Thread
{
	protected int server_port = 4434;
	protected String server_ip = "127.0.0.1";

	public static void main(String[] args) throws Exception {
		new User();
	}

	public User() throws Exception {
		Socket socket = null;
		DataInputStream in = null;
		DataOutputStream out = null;
		
		try {
			System.out.println("[system] connecting to " + server_ip + ":" + server_port + "...");
			
			socket = new Socket(server_ip, server_port); 
			in = new DataInputStream(socket.getInputStream()); 
			out = new DataOutputStream(socket.getOutputStream());

			UserMessageReceiver message_receiver = new UserMessageReceiver(in);
			message_receiver.start();
			
			System.out.println("[system] connected to " + server_ip + ":" + server_port);
		} catch (Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}

		String request;		
		BufferedReader user_input = new BufferedReader(new InputStreamReader(System.in));
			
		while ((request = user_input.readLine()) != null) {
			this.sendMessage(request, out);
		}

		out.close();
		in.close();
		user_input.close();
		socket.close();
	}

	private void sendMessage(String request, DataOutputStream out) {
		try {
			JSONObject req = new JSONObject();
			req.put("reqType", request);
			out.writeUTF(req.toString()); 
			out.flush();
		} catch (IOException e) {
			System.err.println("[system] could not send message");
			e.printStackTrace(System.err);
		}
	}
}

class UserMessageReceiver extends Thread {
	private DataInputStream in;
	private String response;

	public UserMessageReceiver(DataInputStream in) {
		this.in = in;
	}

	public void run() {
		try {
			while ((response = this.in.readUTF()) != null) { 
			
				JSONObject req = new JSONObject(response);
				// REZULTAT ISKANJA

				System.out.println("[server] " + req.getString("reqType")); 
				System.out.print("[user] ");
			}
		} catch (Exception e) {
			System.err.println("[system] could not read message");
			e.printStackTrace(System.err);
		}
	}
}
