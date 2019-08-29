package bottlecap.states.voidstate.tiles;

import bottlecap.states.Handler;

import java.awt.*;

public abstract class TileEntities {

    protected int privateID;
    protected Boolean liteUp = true;
    protected Handler handler;

    public TileEntities(Handler handler) {
        this.handler = handler;
    }

    public int[] cords = new int[2];

    public abstract void tick();

    public abstract void render(Graphics g);

}
