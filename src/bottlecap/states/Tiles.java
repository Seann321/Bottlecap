package bottlecap.states;

import bottlecap.states.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class Tiles {

    public int xDiv;
    private int yDiv;
    private ArrayList<Rectangle> tiles = new ArrayList<>();


    public Tiles(Handler handler) {
        xDiv = handler.getWidth() / 100;
        yDiv = handler.getHeight() / 100;
        int xTiles = handler.getWidth() / xDiv;
        int yTiles = handler.getHeight() / yDiv;
        for (int i = 0; i < xTiles; i++) {
            for(int ii = 0; ii < yTiles; ii++){
                tiles.add(new Rectangle(i * xDiv, ii * yDiv, xDiv, yDiv));
            }
        }
    }

    public int[] cords(int x, int y){
        int[] cords = new int[2];
        cords[0] = (x * xDiv) - xDiv;
        cords[1] = (y * yDiv);
        return cords;
    }

    public int[] tilePOS(int x, int y){
        int[] cords = new int[2];

        cords[0] = x/xDiv;
        cords[1] = y/yDiv;

        return cords;
    }

    public void tick() {
    }

    public void render(Graphics g) {
        for (Rectangle x : tiles) {
            g.setColor(Color.darkGray);
        }
    }

}
