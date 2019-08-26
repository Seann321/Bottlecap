package bottlecap.states.voidstate.tiles;

import bottlecap.states.Handler;

import java.awt.*;

public class CharacterSlots extends TileEntities {

    public Color color;
    public boolean selected = false;
    public Rectangle bounds = new Rectangle();

    public CharacterSlots(int[] cords, Handler handler) {
        super(handler);
        this.cords = cords;
        bounds.x = cords[0];
        bounds.y = cords[1];
        bounds.width = 25;
        bounds.height = 25;
        liteUp = false;
        color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
    }

    @Override
    public void tick() {


    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        if (selected) {
            g.fillRect(cords[0], cords[1], bounds.width, bounds.width);
        }
        g.drawRect(cords[0], cords[1], bounds.width, bounds.width);
    }
}
