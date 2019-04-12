import org.json.JSONObject;

import java.awt.image.BufferedImage;

class ImageProcessing{

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


    public JSONObject processImageSearch(BufferedImage pattern, ImageLoader il) {
        JSONObject location = new JSONObject();
        JSONObject ret = new JSONObject();

        long sumImageFetchTime = 0;

        for(int i = 0; i < ImageLoader.NUM_LOADED_IMAGES; i++) {
            long imageFetchTime = System.currentTimeMillis();
            BufferedImage img = il.getImage(i);
            sumImageFetchTime = System.currentTimeMillis() - imageFetchTime;
            for(int y = 0; y < img.getHeight() - pattern.getHeight() + 1; y++) {
                for(int x = 0; x < img.getWidth() - pattern.getWidth() + 1; x++) {
                    boolean breakout = false;
                    for(int w = 0; w < pattern.getHeight(); w++) {
                        for(int z = 0; z < pattern.getWidth(); z++) {
                            if(pixelManhattanDistance(img.getRGB(x + z, y + w), pattern.getRGB(z, w)) >= 7) {
                                breakout = true;
                                break;
                            }
                        }
                        if (breakout)
                            break;
                    }
                    if (!breakout) {
                        // nismo breakali na nobenem pikslu -> nasli ujemanje!
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

}
