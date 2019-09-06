package bottlecap.states.gameState.inventoryState;

import bottlecap.assets.GUI;
import bottlecap.assets.images.Images;
import bottlecap.states.Handler;
import bottlecap.states.State;
import bottlecap.states.Tiles;

import java.awt.*;
import java.awt.event.KeyEvent;

public class InventoryState extends State {

    Tiles tiles;

    public InventoryState(Handler handler) {
        super(handler);
        gui = new GUI();
        tiles = new Tiles(handler, 64, 36);
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
        g.drawImage(Images.inventoryScreen, 0, 0, handler.getWidth(), handler.getHeight(), null);
        gui.render(g);
    }
}
