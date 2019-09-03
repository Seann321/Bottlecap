package bottlecap.states.gameState;

import bottlecap.states.Handler;
import bottlecap.states.Tiles;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class Player {

    public int AP = 3;
    public int startAP = 3;
    private Color color;
    private Handler handler;
    public int privateID, x, y;
    private Rectangle bounds;
    private Tiles tiles;

    public Player(int[] cords, Color color, Tiles tiles, Handler handler, int privateID) {
        this.tiles = tiles;
        this.color = color;
        this.handler = handler;
        this.privateID = privateID;
        bounds = new Rectangle(cords[0], cords[1], 10, 15);
    }

    public void tick() {
        movement();
    }

    public void movement() {

    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(bounds.x + (int) (tiles.xDiv / 2), bounds.y + (int) (tiles.yDiv / 2), bounds.width, bounds.height);
    }
}
