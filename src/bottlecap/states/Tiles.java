package bottlecap.states;

import java.awt.*;
import java.util.ArrayList;

public class Tiles {

    private final Handler handler;
    private int xTiles, yTiles;
    public static int XDiv;
    public static int YDiv;
    private ArrayList<Rectangle> tiles = new ArrayList<>();


    public Tiles(Handler handler) {
        this.handler = handler;
        XDiv = handler.getWidth() / 100;
        YDiv = handler.getHeight() / 100;
        xTiles = handler.getWidth() / XDiv;
        yTiles = handler.getHeight() / YDiv;
        for (int i = 0; i < xTiles; i++) {
            for (int ii = 0; ii < yTiles; ii++) {
                tiles.add(new Rectangle(i * XDiv, ii * YDiv, XDiv, YDiv));
            }
        }
    }

    public Tiles(Handler handler, int sizeX, int sizeY) {
        this.handler = handler;
        XDiv = handler.getWidth() / sizeX;
        YDiv = handler.getHeight() / sizeY;
        xTiles = handler.getWidth() / XDiv;
        yTiles = handler.getHeight() / YDiv;
        for (int i = 0; i < xTiles; i++) {
            for (int ii = 0; ii < yTiles; ii++) {
                tiles.add(new Rectangle(i * XDiv, ii * YDiv, XDiv, YDiv));
            }
        }
    }

    public int[] cords(int x, int y) {
        int[] cords = new int[2];
        cords[0] = (x * XDiv);
        cords[1] = (y * YDiv);
        return cords;
    }

    public int[] tilePOS(int x, int y) {
        int[] cords = new int[2];

        cords[0] = (x / XDiv);
        cords[1] = y / YDiv;

        return cords;
    }

    public int[] cords(int[] x) {
        int[] cords = new int[2];
        cords[0] = (x[0] * XDiv);
        cords[1] = (x[1] * YDiv);
        return cords;
    }

    public int[] tilePOS(int[] x) {
        int[] cords = new int[2];

        cords[0] = (x[0] / XDiv);
        cords[1] = x[1] / YDiv;

        return cords;
    }

    public void tick() {
    }

    public ArrayList<Rectangle> getTiles() {
        return tiles;
    }

    public void render(Graphics g) {
        for (Rectangle x : tiles) {
            g.setColor(Color.darkGray);
            //g.drawRect(x.x,x.y,x.width,x.height);
        }
    }

}
