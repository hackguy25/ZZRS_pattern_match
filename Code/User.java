import java.io.*;
import java.net.*;

public class User extends Thread
{
	protected int serverPort = 443;

	public static void main(String[] args) throws Exception {
		new User();
	}

	public User() throws Exception {
		Socket socket = null;
		DataInputStream in = null;
		DataOutputStream out = null;
		
		try {
			System.out.println("[system] connecting to chat server ...");
			socket = new Socket("localhost", serverPort); //SSL port=443

			in = new DataInputStream(socket.getInputStream()); // create input stream for listening for incoming messages
			out = new DataOutputStream(socket.getOutputStream()); // create output stream for sending messages

			UserMessageReceiver message_receiver = new UserMessageReceiver(in); // create a separate thread for listening to messages from the chat server
			message_receiver.start(); // run the new thread
		} catch (Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		
		BufferedReader std_in = new BufferedReader(new InputStreamReader(System.in));
		String userInput;
		String message;
			
		while ((userInput = std_in.readLine()) != null) { // read a line from the console 
			this.sendMessage(userInput, out); // send the message to the chat server	
		}

		out.close();
		in.close();
		std_in.close();
		socket.close();
	}

	private void sendMessage(String message, DataOutputStream out) {
		try {
			out.writeUTF(message); // send the message to the chat server
			out.flush(); // ensure the message has been sent
		} catch (IOException e) {
			System.err.println("[system] could not send message");
			e.printStackTrace(System.err);
		}
	}
}

class UserMessageReceiver extends Thread {
	private DataInputStream in;

	public UserMessageReceiver(DataInputStream in) {
		this.in = in;
	}

	public void run() {
		try {
			String message;
			while ((message = this.in.readUTF()) != null) { 
				System.out.println("[RKchat] " + message); 
			}
		} catch (Exception e) {
			System.err.println("[system] could not read message");
			e.printStackTrace(System.err);
		}
	}
}
