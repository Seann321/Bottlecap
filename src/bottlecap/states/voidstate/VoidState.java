package bottlecap.states.voidstate;

import bottlecap.states.Handler;
import bottlecap.states.State;
import bottlecap.states.voidstate.tiles.TileManager;
import bottlecap.states.voidstate.tiles.Tiles;

import java.awt.*;

public class VoidState extends State {

    private Tiles tiles;
    private TileManager tm;


    public VoidState(Handler handler) {
        super(handler);
        tiles = new Tiles(handler);
        tm = new TileManager(handler, tiles);
    }

    @Override
    public void tick() {
        tiles.tick();
        tm.tick();
    }

    @Override
    public void render(Graphics g) {
        tiles.render(g);
        tm.render(g);
        g.setColor(Color.YELLOW);
        if (tm.liteUp) {
            g.drawString("Single Player", tiles.cords(28, 4)[0], tiles.cords(28, 4)[1]);
            g.drawString("Multiplayer", tiles.cords(68, 4)[0], tiles.cords(68, 4)[1]);
        }
    }
}

// public void createDoorways() {
//    newTileEntities.add(new Doorway(handler, tiles.cords(28, 5)));
//     newTileEntities.add(new Doorway(handler, tiles.cords(68, 5)));
//  }