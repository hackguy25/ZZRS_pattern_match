import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

public class RequestHandler {

    public enum ReqType {
        PIXEL_SEARCH,
        PATTERN_SEARCH,
        IMG_SEARCH;

        @Override
        public String toString() {
            return this.name();
        }

        public static ReqType strToType(String s) {
            for(ReqType rt : ReqType.values()) {
                if(rt.toString().equals(s)) {
                    return rt;
                }
            }
            return null;
        }
    }

    public static JSONObject createPixelSearchRequest(long pixel, int reqId) {
        JSONObject ret = new JSONObject();
        ret.put("reqId", reqId);
        ret.put("reqType", ReqType.PIXEL_SEARCH.toString());
        ret.put("pixelValue", pixel);
        ret.put("req_start", System.currentTimeMillis());
        return ret;
    }

    public static JSONObject createPatternSearchRequest(File maskFile, int reqId) {
        JSONObject ret = new JSONObject();
        ret.put("reqId", reqId);
        ret.put("reqType", ReqType.PATTERN_SEARCH.toString());
        ret.put("mask", imgToB64(maskFile));
        return ret;
    }


    public static JSONObject createImageSearchRequest(File image, int reqId) {
        JSONObject ret = new JSONObject();
        ret.put("reqId", reqId);
        ret.put("reqType", ReqType.IMG_SEARCH.toString());
        ret.put("image", imgToB64(image));
        return ret;
    }

    private static String imgToB64(File f) {
        byte[] img;

        try {
            img = Files.readAllBytes(f.toPath());
        } catch (IOException e) {
            System.err.println("Cannot read file");
            e.printStackTrace();
            return null;
        }

        return Base64.getEncoder().encodeToString(img);
    }

    private static byte[] b64ToByteArr(String s) {
        return Base64.getDecoder().decode(s);
    }

    public static JSONObject handleRequest(JSONObject req, ImageProcessing ip, ImageLoader il) {
        JSONObject res;

        long imageProcTime =  System.currentTimeMillis();

        switch (ReqType.strToType(req.getString("reqType"))) {
            case PIXEL_SEARCH:
                res = ip.processPixelSearch(req.getLong("pixelValue"), il);
                break;
            case PATTERN_SEARCH:
                res = ip.processPatternSearch(b64ToByteArr(req.getString("mask")), il);
                break;
            case IMG_SEARCH:
                res = ip.processImageSearch(b64ToByteArr(req.getString("mask")), il);
                break;
            default :
                res = new JSONObject();
                res.put("err", "Invalid request type");
        }
        imageProcTime = System.currentTimeMillis() - imageProcTime - res.getLong("image_fetch_time");
        res.put("reqId", req.getInt("reqId"));
        res.put("req_start", req.getLong("req_start"));
        res.put("proc_time", imageProcTime);
        return res;
    }

}
