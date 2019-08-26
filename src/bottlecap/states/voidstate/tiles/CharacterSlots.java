package bottlecap.states.voidstate.tiles;

import bottlecap.states.Handler;

import java.awt.*;

public class CharacterSlots extends TileEntities {

    public CharacterSlots(int[] cords, Handler handler) {
        super(handler);
        this.cords = cords;
        liteUp = false;
    }

    @Override
    public void tick() {


    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.drawRect(cords[0],cords[1],25,25);
    }
}
