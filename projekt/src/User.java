import java.io.*;
import java.net.*;

import org.json.*;

public class User extends Thread {
    protected int server_port = 4434;
    protected String server_ip = "ec2-52-212-203-50.eu-west-1.compute.amazonaws.com";

    private static int nexReqId = 1;
    private static int receivedRequests = 0;

    public static void main(String[] args) throws Exception {

        if (args.length < 3) {
            System.err.println("Potrebujem 3 argumente: tip zahtev, število zahtev in ime datoteke, kamor naj se rezultati zapisujejo.");
            System.exit(-1);
        }

        new User(Integer.parseInt(args[0]), Integer.parseInt(args[1]), args[2]);
    }

    private void generatePixelSearchRequests(DataInputStream in, DataOutputStream out, JSONArray results, int n) {
        try {
            for(int i = 0; i < n; i++) {

                // create request
                JSONObject req = RequestHandler.createPixelSearchRequest(0xFFFA3881, nexReqId++);
                out.writeUTF(req.toString());
                out.flush();

                // receive result
                JSONObject response = new JSONObject(in.readUTF());
                long reqTime = System.currentTimeMillis() - response.getLong("req_start");
                response.put("reqTime", reqTime);
                results.put(response);

                if (response.has("err")) {
                    System.out.print("[" + response.getInt("reqId") + "] " + response.getString("err"));
                } else {
                    System.out.print("[" + response.getInt("reqId") + "] Found in image " + response.getInt("imageId") + " at ");
                    JSONObject location = response.getJSONObject("location");
                    System.out.print("x: " + location.getInt("x") + ", y: " + location.getInt("y"));
                }
                System.out.println("; Total time: " + reqTime + "ms processing time: " + response.getLong("proc_time") + "ms image loading time: " + response.getLong("image_fetch_time") + "ms");

            }
        } catch (IOException e) {
            System.err.println("[system] could not send message");
            e.printStackTrace();
        }
    }

    private void generateRandomNearRequests(DataInputStream in, DataOutputStream out, JSONArray results, int n) {
        try {
            for(int i = 0; i < n; i++) {

                // create request
                int randomColor = 0xff000000 + (int) (Math.random() * Math.pow(2, 24));
                // distance -> hit rate probability: 50 -> 1%, 85 -> 5%, 107 -> 10%
                JSONObject req = RequestHandler.createPixelNearRequest(randomColor, 50, nexReqId++);
                out.writeUTF(req.toString());
                out.flush();

                // receive result
                JSONObject response = new JSONObject(in.readUTF());
                long reqTime = System.currentTimeMillis() - response.getLong("req_start");
                response.put("reqTime", reqTime);
                results.put(response);

                if (response.has("err")) {
                    System.out.print("[" + response.getInt("reqId") + "] " + response.getString("err"));
                } else {
                    System.out.print("[" + response.getInt("reqId") + "] Found in image " + response.getInt("imageId") + " at ");
                    JSONObject location = response.getJSONObject("location");
                    System.out.print("x: " + location.getInt("x") + ", y: " + location.getInt("y"));
                }
                System.out.println("; Total time: " + reqTime + "ms processing time: " + response.getLong("proc_time") + "ms image loading time: " + response.getLong("image_fetch_time") + "ms");

            }
        } catch (IOException e) {
            System.err.println("[system] could not send message");
            e.printStackTrace();
        }
    }

    private void generateClipSearchRequests(DataInputStream in, DataOutputStream out, JSONArray results, int n) {
        try {
            for(int i = 0; i < n; i++) {

                // create request
                JSONObject req = RequestHandler.createImageSearchRequest(new File("clip1.jpg"), nexReqId++);
                // System.out.println(req.toString());
                out.writeUTF(req.toString());
                out.flush();

                // receive result
                JSONObject response = new JSONObject(in.readUTF());
                long reqTime = System.currentTimeMillis() - response.getLong("req_start");
                response.put("reqTime", reqTime);
                results.put(response);

                if (response.has("err")) {
                    System.out.print("[" + response.getInt("reqId") + "] " + response.getString("err"));
                } else {
                    System.out.print("[" + response.getInt("reqId") + "] Found in image " + response.getInt("imageId") + " at ");
                    JSONObject location = response.getJSONObject("location");
                    System.out.print("x: " + location.getInt("x") + ", y: " + location.getInt("y"));
                }
                System.out.println("; Total time: " + reqTime + "ms processing time: " + response.getLong("proc_time") + "ms image loading time: " + response.getLong("image_fetch_time") + "ms");

            }
        } catch (IOException e) {
            System.err.println("[system] could not send message");
            e.printStackTrace();
        }
    }

    public User(int req_type, int num_reqs, String results_f) throws Exception {
        Socket socket = null;
        DataInputStream in = null;
        DataOutputStream out = null;
//        UserMessageReceiver message_receiver = null;
        JSONArray results = new JSONArray();

        try {
            System.out.println("[system] connecting to " + server_ip + ":" + server_port + "...");

            socket = new Socket(server_ip, server_port);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

//            message_receiver = new UserMessageReceiver(in);
//            message_receiver.start();

            System.out.println("[system] connected to " + server_ip + ":" + server_port);

            switch (req_type) {
                case 0:
                    generatePixelSearchRequests(in, out, results, num_reqs);
                    break;
                case 1:
                    generateRandomNearRequests(in, out, results, num_reqs);
                    break;
                case 2:
                    generateClipSearchRequests(in, out, results, num_reqs);
                    break;
                default:
                    System.err.println("Neveljaven tip zahteve: " + req_type);
                    System.err.println("Možni tipi:");
                    System.err.println(" - 0: iskanje specifičnega piksla");
                    System.err.println(" - 1: iskanje piksla v okolici barve");
                    System.err.println(" - 2: iskanje slikovnega izseka");
                    System.exit(-1);
            }

//            while(receivedRequests < nexReqId - 1)
//                Thread.sleep(500);

        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }

        try {
            FileWriter results_out = new FileWriter(results_f);
            // results_out.write(message_receiver.getResults().toString());
            results_out.write(results.toString());
            results_out.flush();
            results_out.close();

        } catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }

        out.close();
        in.close();
        socket.close();
    }

//    class UserMessageReceiver extends Thread {
//        private DataInputStream in;
//        private String response;
//        private JSONArray results;
//
//        public UserMessageReceiver(DataInputStream in) {
//            this.in = in;
//            this.results = new JSONArray();
//        }
//
//        public void run() {
//            try {
//                while ((response = this.in.readUTF()) != null) {
//
//                    JSONObject res = new JSONObject(response);
//                    // REZULTAT ISKANJA
//
//                    receivedRequests++;
//
//                    long reqTime = System.currentTimeMillis() - res.getLong("req_start");
//                    res.put("reqTime", reqTime);
//
//                    results.put(res);
//
//                    if (res.has("err")) {
//                        System.out.print("[" + res.getInt("reqId") + "] " + res.getString("err"));
//                    } else {
//                        System.out.print("[" + res.getInt("reqId") + "] Found in image " + res.getInt("imageId") + " at ");
//                        JSONObject location = res.getJSONObject("location");
//                        System.out.print("x: " + location.getInt("x") + ", y: " + location.getInt("y"));
//                    }
//                    System.out.println("; Total time: " + reqTime + "ms processing time: " + res.getLong("proc_time") + "ms image loading time: " + res.getLong("image_fetch_time") + "ms");
//                }
//            } catch (Exception e) {
//                if(e instanceof SocketException) {
//                    return;
//                }
//                System.err.println("[system] could not read message");
//                e.printStackTrace(System.err);
//            }
//        }
//
//        public JSONArray getResults() { return results; }
//    }
}
