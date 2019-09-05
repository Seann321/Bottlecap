package bottlecap.states.gameState.inventoryState;

import bottlecap.assets.GUI;
import bottlecap.assets.Text;
import bottlecap.states.Handler;
import bottlecap.states.State;
import bottlecap.states.Tiles;

import java.awt.*;
import java.awt.event.KeyEvent;

public class InventoryState extends State {

    Tiles tiles;
    Text[] uiInfo;

    public InventoryState(Handler handler) {
        super(handler);
        gui = new GUI();
        tiles = new Tiles(handler, 32, 18);
        gui.addText(new Text("Inventory", tiles.cords(16, 1), Text.lFont, true, Color.lightGray, false));
        for (Text t : uiInfo) {
            gui.addText(t);
        }
    }

    @Override
    public void tick() {
        gui.tick();
        if (handler.getKM().keyJustPressed(KeyEvent.VK_E)) {
            handler.setCurrentState(handler.gameState);
        }

    }

    @Override
    public void render(Graphics g) {
        gui.render(g);
        for (Rectangle t : tiles.getTiles()) {
            if (t.getY() < tiles.cords(5, 5)[1]) {
                continue;
            }
            if (t.getY() > tiles.cords(5, 14)[1]) {
                continue;
            }
            if (t.getX() > tiles.cords(27, 14)[0]) {
                continue;
            }
            if (t.getX() < tiles.cords(4, 14)[0]) {
                continue;
            }
            g.setColor(Color.lightGray);
            g.fillRect(t.x, t.y, t.width, t.height);
            g.setColor(Color.darkGray);
            g.drawRect(t.x, t.y, t.width, t.height);
        }
    }
}
