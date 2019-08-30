package bottlecap.states.voidstate.tiles;

import bottlecap.states.Handler;

import java.awt.*;

public class Doorway extends TileEntities{

    Rectangle bounds;
    Boolean singlePlayer = true;

    Doorway(Handler handler, int[] cords){
        super(handler);
        bounds = new Rectangle(cords[0],cords[1],90,150);
        liteUp = false;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(bounds.x,bounds.y,bounds.width,bounds.height);
    }
}
