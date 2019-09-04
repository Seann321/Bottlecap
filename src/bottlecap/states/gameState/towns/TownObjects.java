package bottlecap.states.gameState.towns;

import bottlecap.states.Handler;
import bottlecap.states.Tiles;

import java.awt.*;

public class TownObjects {

    public TownUI townUI;
    private Handler handler;
    private Tiles tiles;
    public int ID = -1;
    public Rectangle bounds;
    public String worldName;

    public TownObjects(Handler handler, Tiles tiles, int ID, Rectangle bounds, String worldName) {
        this.handler = handler;
        this.tiles = tiles;
        this.ID = ID;
        this.worldName = worldName;
        this.bounds = bounds;
        townUI = new TownUI(handler, tiles, ID);
    }

    public void playerEnteringTown() {
        //System.out.println("Player: " + handler.computerID + " entering town with ID: " + ID);
    }

    public void tick() {
        townUI.tick();
    }

    public void render(Graphics g) {
        townUI.render(g);
    }

}
