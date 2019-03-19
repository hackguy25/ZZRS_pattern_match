import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

class ImageProcessing{

	/*BufferedImage image;
	int width;
	int height;

	public ImageProcessing(){
      try {
         File input = new File("test.jpg");
         image = ImageIO.read(input);
         width = image.getWidth();
         height = image.getHeight();

         int count = 0;

         for(int k=0; k<height; k++) {
            System.out.print("\n(R:G:B) Line " + k +": ");
			for(int i=0; i<width; i++) {
               Color c = new Color(image.getRGB(i, k));
               System.out.print(c.getRed() +":"+ c.getGreen() +":"+ c.getBlue() +" ");
            }
         }

		 System.out.println();

      } catch (Exception e) {

	  }
   }*/

	public JSONObject processPixelSearch(long pixel) {
        JSONObject location = new JSONObject();
        JSONObject ret = new JSONObject();

        //TODO pixel search
        location.put("x", 10);
        location.put("y", 5);

        ret.put("imageId", 2);
        ret.put("location", location);

        return ret;
    }

    public JSONObject processPatternSearch(byte[] mask) {
        JSONObject location = new JSONObject();
        JSONObject ret = new JSONObject();

        //TODO pattern search
        location.put("x", 40);
        location.put("y", 2);

        ret.put("imageId", 20);
        ret.put("location", location);

        return ret;
    }

    public JSONObject processImageSearch(byte[] image) {
        JSONObject location = new JSONObject();
        JSONObject ret = new JSONObject();

        //TODO image search
        location.put("x", 110);
        location.put("y", 45);

        ret.put("imageId", 12);
        ret.put("location", location);

        return ret;
    }

}
