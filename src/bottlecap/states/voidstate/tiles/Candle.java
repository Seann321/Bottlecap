package bottlecap.states.voidstate.tiles;

import bottlecap.states.Handler;

import java.awt.*;

public class Candle extends TileEntities{

    private final Handler handler;
    private Rectangle body;
    private Rectangle flame;
    private Boolean special = false;

    public Candle(int x, int y, Handler handler) {
        this.handler = handler;
        body = new Rectangle();
        flame = new Rectangle();
        body.x = x;
        body.y = y;
        body.width = 8;
        body.height = 16;
        flame.x = body.x + body.width/3;
        flame.y = body.y - body.height/2;
        flame.width = body.width/2;
        flame.height = body.height/2;
    }

    public void tick() {
    }

    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(body.x, body.y, body.width, body.height);
        if ((int)(Math.random() * 10) == 1) {
            g.setColor(Color.RED);
        }else{
            g.setColor(Color.orange);
        }
            g.fillRect(flame.x, flame.y, flame.width, flame.height);
    }

}
