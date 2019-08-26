package bottlecap.states.voidstate.tiles;

import bottlecap.states.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class TileManager {


    private ArrayList<TileEntities> tiles = new ArrayList<>();
    private ArrayList<TileEntities> newTiles = new ArrayList<>();
    private Handler handler;
    private final Tiles TILES;


    public TileManager(Handler handler, Tiles TILES) {
        this.handler = handler;
        this.TILES = TILES;
        newTiles.add(new Candle(TILES.cords(47,85)[0],TILES.cords(47,85)[1],handler));
        newTiles.add(new Candle(TILES.cords(53,85)[0],TILES.cords(53,85)[1],handler));
    }

    public void tick() {

        tiles.clear();
        tiles.addAll(newTiles);
        Iterator<TileEntities> it = tiles.iterator();
        while (it.hasNext()) {
            TileEntities x = it.next();
            x.tick();
        }

    }

    public void render(Graphics g) {

        Iterator<TileEntities> it = tiles.iterator();
        while (it.hasNext()) {
            TileEntities x = it.next();
            x.render(g);
        }
    }

}
