package bottlecap.assets.images;

import java.awt.image.BufferedImage;

public class Images {

    public static final int WIDTH = 8, HEIGHT = 8;

    public static BufferedImage water, forest,bridge,dessertTown,grassTown;
    public static BufferedImage[] grass = new BufferedImage[3];
    public static BufferedImage[] dessert = new BufferedImage[3];

    public static void init() {

        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("SpriteSheet.png"));

        grass[0] = sheet.crop(0, 0, WIDTH, HEIGHT);
        grass[1] = sheet.crop(0, HEIGHT, WIDTH, HEIGHT);
        grass[2] = sheet.crop(0, HEIGHT*2, WIDTH, HEIGHT);
        water = sheet.crop(WIDTH, 0, WIDTH, HEIGHT);
        forest = sheet.crop(WIDTH*2, 0, WIDTH, HEIGHT);
        dessert[0] = sheet.crop(WIDTH*3, 0, WIDTH, HEIGHT);
        dessert[1] = sheet.crop(WIDTH*3, HEIGHT, WIDTH, HEIGHT);
        dessert[2] = sheet.crop(WIDTH*3, HEIGHT*2, WIDTH, HEIGHT);
        bridge = sheet.crop(WIDTH*4, 0, WIDTH, HEIGHT);
        dessertTown = sheet.crop(WIDTH*5, 0, WIDTH, HEIGHT);
        grassTown = sheet.crop(WIDTH*6, 0, WIDTH, HEIGHT);
    }

}
