package bottlecap.states;

import java.awt.*;
import java.util.ArrayList;

public class Tiles {

    private final Handler handler;
    private int xTiles, yTiles;
    public int xDiv;
    public int yDiv;
    private ArrayList<Rectangle> tiles = new ArrayList<>();


    public Tiles(Handler handler) {
        this.handler = handler;
        xDiv = handler.getWidth() / 100;
        yDiv = handler.getHeight() / 100;
        xTiles = handler.getWidth() / xDiv;
        yTiles = handler.getHeight() / yDiv;
        for (int i = 0; i < xTiles; i++) {
            for (int ii = 0; ii < yTiles; ii++) {
                tiles.add(new Rectangle(i * xDiv, ii * yDiv, xDiv, yDiv));
            }
        }
    }

    public Tiles(Handler handler, int sizeX, int sizeY) {
        this.handler = handler;
        xDiv = handler.getWidth() / sizeX;
        yDiv = handler.getHeight() / sizeY;
        xTiles = handler.getWidth() / xDiv;
        yTiles = handler.getHeight() / yDiv;
        for (int i = 0; i < xTiles; i++) {
            for (int ii = 0; ii < yTiles; ii++) {
                tiles.add(new Rectangle(i * xDiv, ii * yDiv, xDiv, yDiv));
            }
        }
    }

    public int[] cords(int x, int y) {
        int[] cords = new int[2];
        cords[0] = (x * xDiv);
        cords[1] = (y * yDiv);
        return cords;
    }

    public int[] tilePOS(int x, int y) {
        int[] cords = new int[2];

        cords[0] = (x / xDiv);
        cords[1] = y / yDiv;

        return cords;
    }

    public int[] cords(int[] x) {
        int[] cords = new int[2];
        cords[0] = (x[0] * xDiv);
        cords[1] = (x[1] * yDiv);
        return cords;
    }

    public int[] tilePOS(int[] x) {
        int[] cords = new int[2];

        cords[0] = (x[0] / xDiv);
        cords[1] = x[1] / yDiv;

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
