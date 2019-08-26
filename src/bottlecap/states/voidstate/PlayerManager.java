package bottlecap.states.voidstate;

import bottlecap.states.Handler;
import bottlecap.states.gamestate.Square;
import bottlecap.states.voidstate.tiles.Tiles;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class PlayerManager {

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Player> newPlayers = new ArrayList<>();
    private Handler handler;
    private Tiles tiles;


    public PlayerManager(Handler handler, Tiles tiles) {
        this.handler = handler;
        this.tiles = tiles;
        newPlayers.add(new Player(new Rectangle((tiles.cords(50,85)[0]) - tiles.xDiv/2, tiles.cords(50,85)[1], 20, 30), handler));
    }

    public void tick() {
        players.clear();
        players.addAll(newPlayers);
        Iterator<Player> it = players.iterator();
        while (it.hasNext()) {
            Player x = it.next();
            x.tick();
        }
    }

    public void render(Graphics g) {

        Iterator<Player> it = players.iterator();
        while (it.hasNext()) {
            Player x = it.next();
            x.render(g);
        }
    }

}



