package bottlecap.states.voidstate.tiles;

import bottlecap.states.Handler;

import java.awt.*;

public abstract class TileEntities {

    public int privateID = 0;
    public Boolean liteUp = true;
    protected Handler handler;
    int[] cords = new int[2];

    TileEntities(Handler handler) {
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

}
