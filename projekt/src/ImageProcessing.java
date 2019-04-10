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

        long sumImageFetchTime = 0;

        for(int i = 0; i < ImageLoader.NUM_LOADED_IMAGES; i++) {
            long imageFetchTime = System.currentTimeMillis();
            BufferedImage img = il.getImage(i);
            sumImageFetchTime = System.currentTimeMillis() - imageFetchTime;
            for(int y = 0; y < img.getHeight(); y++) {
                for(int x = 0; x < img.getWidth(); x++) {
                    if(img.getRGB(x, y) == pixel) {
                        location.put("x", x);
                        location.put("y", y);
                        ret.put("imageId", i + 1);
                        ret.put("location", location);
                        ret.put("image_fetch_time", sumImageFetchTime);
                        return ret;
                    }
                }
            }
        }

        ret.put("err", "No image found");

        return ret;
    }

    private long pixelManhattanDistance(long p1, long p2) {

	    long delta_r = (p1 & 0x0000000000ff0000) >> 16;
	    delta_r -= (p2 & 0x0000000000ff0000) >> 16;
	    delta_r = (delta_r < 0) ? -delta_r : delta_r;

        long delta_g = (p1 & 0x000000000000ff00) >> 8;
        delta_g -= (p2 & 0x000000000000ff00) >> 8;
        delta_g = (delta_g < 0) ? -delta_g : delta_g;

        long delta_b = p1 & 0x00000000000000ff;
        delta_b -= p2 & 0x00000000000000ff;
        delta_b = (delta_b < 0) ? -delta_b : delta_b;

        return delta_r + delta_g + delta_b;
    }

    public JSONObject processPixelNear(long pixel, long dist, ImageLoader il) {
        JSONObject location = new JSONObject();
        JSONObject ret = new JSONObject();

        long sumImageFetchTime = 0;

        for(int i = 0; i < ImageLoader.NUM_LOADED_IMAGES; i++) {
            long imageFetchTime = System.currentTimeMillis();
            BufferedImage img = il.getImage(i);
            sumImageFetchTime = System.currentTimeMillis() - imageFetchTime;
            for(int y = 0; y < img.getHeight(); y++) {
                for(int x = 0; x < img.getWidth(); x++) {
                    if(pixelManhattanDistance(img.getRGB(x, y), pixel) <= dist) {
                        location.put("x", x);
                        location.put("y", y);
                        ret.put("imageId", i + 1);
                        ret.put("location", location);
                        ret.put("image_fetch_time", sumImageFetchTime);
                        return ret;
                    }
                }
            }
        }

        ret.put("err", "No suitable pixels found");

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
