package bottlecap.states.gameState.worldGenerator;

import bottlecap.assets.images.Images;

import java.awt.*;
import java.util.Random;

public class WorldTiles {

    public static enum TileType {
        GRASS, WATER
    }

    public int x, y;
    private int texture = -1;
    private TileType tileType;

    public WorldTiles(int[] cords, int z) {
        x = cords[0];
        y = cords[1];
        if (x < 0 || y < 0) {
            System.out.println("Negative Tile at X " + x + " Y " + y);
        }
        if (z == 0) {
            tileType = TileType.GRASS;
            Random tempRandom = new Random();
            int temp = tempRandom.nextInt(3);
            texture = temp;
        } else if (z == 1) {
            tileType = TileType.WATER;
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {
        if (tileType == TileType.WATER) {
            g.drawImage(Images.water, x, y, 64, 36, null);
        } else if(tileType == TileType.GRASS){
            g.drawImage(Images.grass[texture],x,y,64,36,null);
        }
    }
}
