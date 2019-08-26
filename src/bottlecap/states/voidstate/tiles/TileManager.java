package bottlecap.states.voidstate.tiles;

import bottlecap.states.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class TileManager {


    private ArrayList<TileEntities> tileEntities = new ArrayList<>();
    private ArrayList<TileEntities> newTileEntities = new ArrayList<>();
    private Handler handler;
    private final Tiles tiles;
    public Boolean liteUp = false;

    public TileManager(Handler handler, Tiles tiles) {
        this.handler = handler;
        this.tiles = tiles;
        newTileEntities.add(new Player(new Rectangle((tiles.cords(50, 85)[0]) - tiles.xDiv / 2, tiles.cords(50, 85)[1], 20, 30), handler));
        createCandleWalkway();
        newTileEntities.add(new Candle(tiles.cords(50, 10), true, handler));
        createDoorways();
        createPlayerSlots();
    }


    public void tick() {

        tileEntities.clear();
        tileEntities.addAll(newTileEntities);
        Iterator<TileEntities> it = tileEntities.iterator();
        while (it.hasNext()) {
            TileEntities x = it.next();
            x.tick();
        }
        lightUpCandles();
        if (liteUp) {
            collisionWithDoors();
        }
    }

    public void startSinglePlayer() {

    }

    public void startMutliplayer() {

    }

    public void createPlayerSlots() {
        //Singleplayer Slots
        newTileEntities.add(new CharacterSlots(tiles.cords(25,50),handler));
        newTileEntities.add(new CharacterSlots(tiles.cords(25,55),handler));
        newTileEntities.add(new CharacterSlots(tiles.cords(35,50),handler));
        newTileEntities.add(new CharacterSlots(tiles.cords(35,55),handler));
        //Multiplayer Slots
        newTileEntities.add(new CharacterSlots(tiles.cords(65,50),handler));
        newTileEntities.add(new CharacterSlots(tiles.cords(65,55),handler));
        newTileEntities.add(new CharacterSlots(tiles.cords(75,50),handler));
        newTileEntities.add(new CharacterSlots(tiles.cords(75,55),handler));
    }

    public void collisionWithDoors() {
        for (TileEntities d : tileEntities) {
            if (d instanceof Doorway) {
                for (TileEntities p : tileEntities) {
                    if (p instanceof Player) {
                        if (((Doorway) d).bounds.intersects(((Player) p).getBounds())) {
                            if (((Doorway) d).singlePlayer) {
                                startSinglePlayer();
                            }
                            if (!((Doorway) d).singlePlayer) {
                                startMutliplayer();
                            }
                        }
                    }
                }
            }
        }
    }


    public void createDoorways() {
        newTileEntities.add(new Doorway(handler, tiles.cords(28, 5)));
        newTileEntities.add(new Doorway(handler, tiles.cords(68, 5), false));
    }

    public void createCandleWalkway() {
        for (int i = 40; i <= 90; i += 10) {
            newTileEntities.add(new Candle(tiles.cords(47, i), handler));
            newTileEntities.add(new Candle(tiles.cords(53, i), handler));
        }
        for (int i = 10; i <= 90; i += 5) {
            newTileEntities.add(new Candle(tiles.cords(i, 40), handler));
            newTileEntities.add(new Candle(tiles.cords(i, 15), handler));
        }
        tileEntities.addAll(newTileEntities);
        deleteDumbCandles();
    }

    public void deleteDumbCandles() {
        for (TileEntities t : tileEntities) {
            if (t.cords[0] == tiles.cords(50, 40)[0]) {
                newTileEntities.remove(t);
            }
            if (t.cords[0] == tiles.cords(30, 15)[0]) {
                newTileEntities.remove(t);
            }
            if (t.cords[0] == tiles.cords(70, 15)[0]) {
                newTileEntities.remove(t);
            }
        }
    }

    public void lightUpCandles() {

        if (true) {
            for (TileEntities p : tileEntities) {
                p.liteUp = true;
                liteUp = true;
            }

            return;
        }

        double radius = 3.5;

        for (TileEntities p : tileEntities) {
            if (p instanceof Player) {
                for (TileEntities c : tileEntities) {
                    if (c instanceof Candle) {
                        if (((Candle) c).liteUp) continue;
                        if (c.cords[0] >= p.cords[0] - (((Player) p).getBounds().getWidth() * radius) && c.cords[0] <= p.cords[0] + (((Player) p).getBounds().getWidth() * radius)) {
                            if (c.cords[1] >= p.cords[1] - (((Player) p).getBounds().getHeight() * radius) && c.cords[1] <= p.cords[1] + (((Player) p).getBounds().getHeight() * radius)) {
                                ((Candle) c).liteUp = true;
                                if (((Candle) c).special) {
                                    for (TileEntities cc : tileEntities) {
                                        cc.liteUp = true;
                                        ((Candle) c).special = false;
                                        liteUp = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    public void render(Graphics g) {

        Iterator<TileEntities> it = tileEntities.iterator();
        while (it.hasNext()) {
            TileEntities x = it.next();
            if (x.liteUp)
                x.render(g);
        }
    }

}
