import java.io.*;
import java.net.*;
import java.util.ArrayList;

import org.json.*;


public class ContinuousRequester extends Thread {

    protected int server_port = 4434;
    protected String server_ip = "ec2-52-212-203-50.eu-west-1.compute.amazonaws.com";

    private static int nexReqId = 1;
    private static int receivedRequests = 0;

    private double testDelay;
    private int color;
    private String resultsFName;

    public static void main(String[] args) throws Exception {

        if (args.length < 4) {
            System.err.println("Potrebujem 4 argumente: barvo, čas testiranja posamezne intenzivnosti (sekunde), "
                    + "začetno intenzivnost (sekund med zahtevami) in ime datoteke, kamor naj se rezultati zapisujejo.");
            System.exit(-1);
        }

        int color = Integer.parseUnsignedInt(args[0], 16);
        int testTime = 1000 * Integer.parseInt(args[1]);
        double baseTestDelay = 1000. * Long.parseLong(args[2]);
        int intensityMultiplier = 1;
        String resultFileName = args[3];

        ContinuousRequester cr = new ContinuousRequester(baseTestDelay / intensityMultiplier, color, resultFileName);
        cr.start();

        while (true) {

            cr.setTestDelay(baseTestDelay / intensityMultiplier);

            Thread.sleep(testTime);

            intensityMultiplier++;
        }
    }

    public void run() {

        ArrayList<RequestSender> threads = new ArrayList<>();

        try {
            FileWriter results_out = new FileWriter(resultsFName);
            results_out.flush();
            results_out.close();
        } catch (Exception e) {
            System.err.println("[system] can't reset results file");
            e.printStackTrace();
        }

        while (true) {

            RequestSender rs = new RequestSender();
            rs.start();
            threads.add(rs);

            try {
                Thread.sleep((long)testDelay);
            } catch (Exception e) {
                System.err.println("[system] can't sleep");
                e.printStackTrace();
            }
        }
    }

    public ContinuousRequester(double delay, int color, String resultsFName) {

        this.testDelay = delay;
        this.color = color;
        this.resultsFName = resultsFName;
    }

    public void setTestDelay(double delay) {

        this.testDelay = delay;
    }

    class RequestSender extends Thread {

        public RequestSender(){

        }

        public void run() {

            try {

                // create socket, data streams
                Socket socket = new Socket(server_ip, server_port);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                // create request
                JSONObject req = RequestHandler.createPixelNearRequest(color, 5, nexReqId++);
                out.writeUTF(req.toString());
                out.flush();

                // receive result
                JSONObject response = new JSONObject(in.readUTF());
                long reqTime = System.currentTimeMillis() - response.getLong("req_start");
                response.put("reqTime", reqTime);

                // append result
                FileWriter results_out = new FileWriter(resultsFName, true);
                results_out.write(response.toString());
                results_out.write("\n");
                results_out.flush();
                results_out.close();

                // print out result
                if (response.has("err")) {
                    System.out.print("[" + response.getInt("reqId") + "] " + response.getString("err"));
                } else {
                    System.out.print("[" + response.getInt("reqId") + "] Found in image " +
                            response.getInt("imageId") + " at ");
                    JSONObject location = response.getJSONObject("location");
                    System.out.print("x: " + location.getInt("x") + ", y: " + location.getInt("y"));
                }
                System.out.println("; Total time: " + reqTime + "ms processing time: " +
                        response.getLong("proc_time") + "ms image loading time: " +
                        response.getLong("image_fetch_time") + "ms");
            } catch (Exception e) {
                System.err.println("[system] could not send message");
                e.printStackTrace();
            }
        }
    }
}