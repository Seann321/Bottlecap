package bottlecap.states;

import bottlecap.states.Handler;

import java.awt.*;
import java.util.ArrayList;

public class Tiles {

    private final Handler handler;
    private int xTiles, yTiles;
    public double xDiv;
    private double yDiv;
    private ArrayList<Rectangle> tiles = new ArrayList<>();


    public Tiles(Handler handler) {
        this.handler = handler;
        xDiv = handler.getWidth() / 100D;
        yDiv = handler.getHeight() / 100D;
        xTiles = (int)(handler.getWidth() / xDiv);
        yTiles = (int)(handler.getHeight() / yDiv);
        for (int i = 0; i < xTiles; i++) {
            for(int ii = 0; ii < yTiles; ii++){
                tiles.add(new Rectangle((int)(i * xDiv), (int)(ii * yDiv), (int)xDiv, (int)yDiv));
            }
        }
        //System.out.println(xDiv);
    }

    public int[] cords(int x, int y){
        int[] cords = new int[2];
        cords[0] = (int)((x * xDiv) - xDiv);
        cords[1] = (int)((y * yDiv));
        return cords;
    }

    public int[] tilePOS(int x, int y){
        int[] cords = new int[2];

        cords[0] = (int)(x/xDiv);
        cords[1] = (int)(y/yDiv);

        return cords;
    }

    public void tick() {
    }

    public void render(Graphics g) {
        for (Rectangle x : tiles) {
            g.setColor(Color.darkGray);
            //g.drawRect(x.x,x.y,x.width,x.height);
        }
    }

}
