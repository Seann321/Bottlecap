package bottlecap.states.voidstate.tiles;

import bottlecap.states.Handler;

import java.awt.*;

public class CharacterSlots extends TileEntities {

    public boolean fastTravelUnlocked = false;
    public int health = 100;
    public int level = 0;
    public int pos;
    public Color color;
    public boolean selected = false;
    public Rectangle bounds = new Rectangle();
    public String nickName = "JEFF THE CLASSLESS";

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
        handler.fileSystem.writeToFile("CHARSLOT" + pos + color + "LVL" + level + "HP" + health +"NICK" + nickName);
    }

    public CharacterSlots(int[] cords, Handler handler, int pos, Color color, String chars) {
        super(handler);
        this.pos = pos;
        this.cords = cords;
        bounds.x = cords[0];
        bounds.y = cords[1];
        bounds.width = 25;
        bounds.height = 25;
        liteUp = false;
        this.color = color;
        this.level = Integer.parseInt(chars.substring(chars.indexOf("LVL") + 3, chars.indexOf("HP")));
        this.nickName = chars.substring(chars.indexOf("NICK") + 4);
        this.health = Integer.parseInt(chars.substring(chars.indexOf("HP") + 2,chars.indexOf("NICK")));
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
