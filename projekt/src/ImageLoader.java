import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {

    public static final int NUM_LOADED_IMAGES = 20;
    public static final int NUM_IMAGES = 238;
    public static final boolean CACHING_ENABLED = false;

    public ImageLoader() {
        if (CACHING_ENABLED) loadImages();
    }

    private volatile BufferedImage[] images = new BufferedImage[NUM_LOADED_IMAGES];

    private void loadImages() {
        for(int i = 0; i < NUM_LOADED_IMAGES; i++) {
            try {
                File f = new File("./res/" + Integer.toString(i + 1) + ".jpg");
                images[i] = ImageIO.read(f);
            } catch (IOException e) {
                e.printStackTrace();
                System.err.println("Couldnt load image " + (i + 1));
            }
        }
    }

    public BufferedImage getImage(int id) {
        if(CACHING_ENABLED && id < 30) return images[id];
        try {
            return ImageIO.read(new File("./res/" + Integer.toString(id + 1) + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
            return images[0];
        }
    }

}
