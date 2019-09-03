package bottlecap.states.gameState.worldGenerator;

import bottlecap.assets.images.Images;
import bottlecap.states.Handler;
import bottlecap.states.Tiles;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class WorldTiles {

    public static enum TileType {
        GRASS, WATER, FOREST, DESSERT, BRIDGE, DESSERTTOWN, GRASSTOWN
    }

    Tiles gridPlacement;
    public int x, y, z;
    private int texture = -1;
    public TileType tileType;
    Tiles tiles;
    public Rectangle bounds;
    private Handler handler;
    public static boolean debug = false;

    public WorldTiles(Tiles tiles, int[] cords, int z, Handler handler) {
        this.tiles = tiles;
        this.handler = handler;
        x = cords[0];
        y = cords[1];
        this.z = z;
        bounds = new Rectangle(x, y, (int) tiles.xDiv, (int) tiles.yDiv);
        if (x < 0 || y < 0) {
            System.out.println("Negative Tile at X " + x + " Y " + y);
        }
        Random tempRandom = new Random();
        int temp = tempRandom.nextInt(3);
        texture = temp;
        switch (z) {
            case 0:
                tileType = TileType.GRASS;
                break;
            case 1:
                tileType = TileType.WATER;
                break;
            case 2:
                tileType = TileType.FOREST;
                break;
            case 3:
                tileType = TileType.DESSERT;
                break;
            case 4:
                tileType = TileType.BRIDGE;
                break;
            case 5:
                tileType = TileType.DESSERTTOWN;
                break;
            case 6:
                tileType = TileType.GRASSTOWN;
                break;
        }
    }

    public static TileType activeMapPainter = TileType.WATER;

    public void tick() {
        if(debug){
            paintMap();
        }
    }

    public void paintMap(){
        if (handler.getKM().keyJustPressed(KeyEvent.VK_0)) {
            activeMapPainter = TileType.WATER;
        } else if (handler.getKM().keyJustPressed(KeyEvent.VK_1)) {
            activeMapPainter = TileType.GRASS;
        } else if (handler.getKM().keyJustPressed(KeyEvent.VK_2)) {
            activeMapPainter = TileType.FOREST;
        } else if (handler.getKM().keyJustPressed(KeyEvent.VK_3)) {
            activeMapPainter = TileType.DESSERT;
        } else if (handler.getKM().keyJustPressed(KeyEvent.VK_4)) {
            activeMapPainter = TileType.BRIDGE;
        } else if (handler.getKM().keyJustPressed(KeyEvent.VK_5)) {
            activeMapPainter = TileType.DESSERTTOWN;
        } else if (handler.getKM().keyJustPressed(KeyEvent.VK_6)) {
            activeMapPainter = TileType.GRASSTOWN;
        }
        if (handler.getMM().isLeftPressed()) {
            if (bounds.contains(handler.getMM().getMouseX(), handler.getMM().getMouseY())) {
                tileType = activeMapPainter;
            }
        }
    }

    public void render(Graphics g) {
        switch (tileType) {
            case WATER:
                g.drawImage(Images.water, x, y, (int) tiles.xDiv, (int) tiles.yDiv, null);
                break;
            case GRASS:
                g.drawImage(Images.grass[texture], x, y, (int) tiles.xDiv, (int) tiles.yDiv, null);
                break;
            case FOREST:
                g.drawImage(Images.forest, x, y, (int) tiles.xDiv, (int) tiles.yDiv, null);
                break;
            case DESSERT:
                g.drawImage(Images.dessert[texture], x, y, (int) tiles.xDiv, (int) tiles.yDiv, null);
                break;
            case BRIDGE:
                g.drawImage(Images.bridge, x, y, (int) tiles.xDiv, (int) tiles.yDiv, null);
                break;
            case DESSERTTOWN:
                g.drawImage(Images.dessertTown, x, y, (int) tiles.xDiv, (int) tiles.yDiv, null);
                break;
            case GRASSTOWN:
                g.drawImage(Images.grassTown, x, y, (int) tiles.xDiv, (int) tiles.yDiv, null);
                break;

        }
    }
}
