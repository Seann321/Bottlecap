package bottlecap.states.voidstate;

import bottlecap.states.Handler;
import bottlecap.states.State;
import bottlecap.states.voidstate.tiles.Candle;
import bottlecap.states.voidstate.tiles.TileManager;
import bottlecap.states.voidstate.tiles.Tiles;

import java.awt.*;

public class VoidState extends State {

    private PlayerManager playerManager;
    private Tiles tiles;
    private TileManager tm;

    public VoidState(Handler handler) {
        super(handler);
        tiles = new Tiles(handler);
        tm = new TileManager(handler,tiles);
        playerManager = new PlayerManager(handler, tiles);
    }

    @Override
    public void tick() {
        tiles.tick();
        playerManager.tick();
        tm.tick();
    }

    @Override
    public void render(Graphics g) {
        tiles.render(g);
        playerManager.render(g);
        tm.render(g);
    }
}
