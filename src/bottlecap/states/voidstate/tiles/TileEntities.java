package bottlecap.states.voidstate.tiles;

import java.awt.*;

public abstract class TileEntities {

    public int x,y;

    public abstract void tick();

    public abstract void render(Graphics g);

}
