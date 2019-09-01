package bottlecap.states.voidstate.tiles;

import bottlecap.states.Handler;

import java.awt.*;

public class CharacterSlots extends TileEntities {

    public int level = 0;
    public int pos;
    public Color color;
    public boolean selected = false;
    public Rectangle bounds = new Rectangle();
    public String nickName = "CLASSLESS";

    public CharacterSlots(int[] cords, Handler handler, int pos) {
        super(handler);
        this.pos = pos;
        this.cords = cords;
        bounds.x = cords[0];
        bounds.y = cords[1];
        bounds.width = 25;
        bounds.height = 25;
        liteUp = false;
        color = new Color((int) (Math.random() * 89) + 10, (int) (Math.random() * 89) + 10, (int) (Math.random() * 89) + 10);
        handler.fileSystem.writeToFile("CHARSLOT" + pos + color + "LVL" + level + "NICK" + nickName);
    }

    public CharacterSlots(int[] cords, Handler handler, int pos, Color color, int level, String nickName) {
        super(handler);
        this.pos = pos;
        this.cords = cords;
        bounds.x = cords[0];
        bounds.y = cords[1];
        bounds.width = 25;
        bounds.height = 25;
        liteUp = false;
        this.color = color;
        this.level = level;
        this.nickName = nickName;
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
