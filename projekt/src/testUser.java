import org.json.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class testUser {

    // Class za random testiranje, pls uporabljaj za lomljenje škatel ali pa smo ignore

    private static byte[] b64ToByteArr(String s) {
        return Base64.getDecoder().decode(s);
    }

    public static void main (String [] args) {

        JSONObject req = RequestHandler.createImageSearchRequest(new File("testPattern.png"), 1);
        System.out.println(req.toString());
        System.out.println(b64ToByteArr(req.getString("image")).length);

        ByteArrayInputStream imgStream = new ByteArrayInputStream(b64ToByteArr(req.getString("image")));
        BufferedImage img;
        try {

            img = ImageIO.read(imgStream);
            System.out.println(img.toString());

            System.out.println(img.getData().toString());

            System.out.println(Integer.toHexString(img.getRGB(1, 3)));
        } catch (java.io.IOException e) {
            System.out.println(e.toString());
        }
        System.out.flush();
    }
}
