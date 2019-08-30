package bottlecap.states.voidstate.tiles;

import bottlecap.states.Handler;

import java.awt.*;

public class Candle extends TileEntities {

    private Rectangle body;
    private Rectangle flame;
    Boolean special = false;

    Candle(int[] cords, Handler handler) {
        super(handler);
        setup(cords);
    }

    Candle(int[] cords, Boolean special, Handler handler) {
        super(handler);
        setup(cords);
        if (special) {
            body.width *= 4;
            body.height *= 4;
            flame.x = body.x + body.width / 3;
            flame.y = body.y - body.height / 2;
            flame.width = body.width / 2;
            flame.height = body.height / 2;
        }
        this.special = special;
    }

    private void setup(int[] cords) {
        this.cords = cords;
        body = new Rectangle();
        flame = new Rectangle();
        body.x = cords[0];
        body.y = cords[1];
        body.width = 8;
        body.height = 16;
        flame.x = body.x + body.width / 3;
        flame.y = body.y - body.height / 2;
        flame.width = body.width / 2;
        flame.height = body.height / 2;
        liteUp = false;
    }

    public void tick() {
    }

    public void render(Graphics g) {
        if (!liteUp) return;
        g.setColor(Color.white);
        g.fillRect(body.x, body.y, body.width, body.height);
        if ((int) (Math.random() * 10) == 1) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.orange);
        }
        g.fillRect(flame.x, flame.y, flame.width, flame.height);
    }

}
