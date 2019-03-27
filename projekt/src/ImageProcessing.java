import org.json.JSONObject;

import java.awt.image.BufferedImage;

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

	public JSONObject processPixelSearch(long pixel, ImageLoader il) {
        JSONObject location = new JSONObject();
        JSONObject ret = new JSONObject();

        //TODO pixel search
        for(int i = 0; i < ImageLoader.NUM_LOADED_IMAGES; i++) {
            BufferedImage img = il.getImage(i);
            for(int y = 0; y < img.getHeight(); y++) {
                for(int x = 0; x < img.getWidth(); x++) {
                    if(img.getRGB(x, y) == pixel) {
                        location.put("x", x);
                        location.put("y", y);
                        ret.put("imageId", i + 1);
                        ret.put("location", location);
                        return ret;
                    }
                }
            }
        }

        ret.put("err", "No image found");

        return ret;
    }

    public JSONObject processPatternSearch(byte[] mask, ImageLoader il) {
        JSONObject location = new JSONObject();
        JSONObject ret = new JSONObject();

        //TODO pattern search
        location.put("x", 40);
        location.put("y", 2);

        ret.put("imageId", 20);
        ret.put("location", location);

        return ret;
    }

    public JSONObject processImageSearch(byte[] image, ImageLoader il) {
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
