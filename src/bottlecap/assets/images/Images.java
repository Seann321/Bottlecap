package bottlecap.assets.images;

import java.awt.image.BufferedImage;

public class Images {

    public static final int WIDTH = 8, HEIGHT = 8;

    public static BufferedImage water;
    public static BufferedImage[] grass = new BufferedImage[3];

    public static void init() {

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("SpriteSheet.png"));

        grass[0] = sheet.crop(0, 0, WIDTH, HEIGHT);
        grass[1] = sheet.crop(0, HEIGHT, WIDTH, HEIGHT);
        grass[2] = sheet.crop(0, HEIGHT*2, WIDTH, HEIGHT);
        water = sheet.crop(WIDTH, 0, WIDTH, HEIGHT);

    }

}
