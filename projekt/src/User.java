import java.io.*;
import java.net.*;

import org.json.*;

public class User extends Thread {
    protected int server_port = 4434;
    protected String server_ip = "34.244.68.191";

    private static int nexReqId = 1;
    private static int receivedRequests = 0;

    public static void main(String[] args) throws Exception {
        new User();
    }

    private void generateRequests(DataOutputStream out) {
        try {
            for(int i = 0; i < 5; i++) {
                JSONObject req = RequestHandler.createPixelSearchRequest(0xFFFA3881, nexReqId++);
                out.writeUTF(req.toString());
                out.flush();
            }
        } catch (IOException e) {
            System.err.println("[system] could not send message");
            e.printStackTrace();
        }
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


            generateRequests(out);

            while(receivedRequests < nexReqId - 1)
                Thread.sleep(500);

        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }


        out.close();
        in.close();
        socket.close();
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

                    JSONObject res = new JSONObject(response);
                    // REZULTAT ISKANJA

                    receivedRequests++;

                    long reqTime = System.currentTimeMillis() - res.getLong("req_start");

                    if (res.has("err")) {
                        System.out.println("[" + res.getInt("reqId") + "] " + res.getString("err"));
                    } else {
                        System.out.print("[" + res.getInt("reqId") + "] Found in image " + res.getInt("imageId") + " at ");
                        JSONObject location = res.getJSONObject("location");
                        System.out.print("x: " + location.getInt("x") + ", y: " + location.getInt("y"));
                        System.out.println(" Total time: " + reqTime + "ms processing time: " + res.getLong("proc_time") + "ms image loading time: " + res.getLong("image_fetch_time") + "ms");
                    }
                }
            } catch (Exception e) {
                if(e instanceof SocketException) {
                    return;
                }
                System.err.println("[system] could not read message");
                e.printStackTrace(System.err);
            }
        }
    }
}
