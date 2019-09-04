package bottlecap.states.gameState.towns;

import bottlecap.assets.Text;
import bottlecap.states.Handler;
import bottlecap.states.Tiles;

import java.awt.*;
import java.util.ArrayList;

public class TownUI {

    private int ID;
    private Tiles tiles;
    private Handler handler;
    private ArrayList<Text> townText = new ArrayList<>();
    private Standing standing;

    public TownUI(Handler handler, Tiles tiles, int ID) {
        standing = Standing.NEUTRAL;
        this.handler = handler;
        this.tiles = tiles;
        this.ID = ID;
        townText.add(new Text("Welcome to the Town of " + ID, tiles.cords(32, 5), Text.lFont, true, Color.white, false));
        townText.add(new Text("Current Standing: " + standing, tiles.cords(32, 6), Text.sFont, true, Color.white, false));
    }

    public void tick() {
        for (Text t : townText) {
            t.tick();
        }
    }

    public void render(Graphics g) {
        g.setColor(new Color(10, 10, 10, 100));
        g.fillRect(tiles.cords(4, 4)[0], tiles.cords(4, 4)[1], tiles.cords(56, 28)[0], tiles.cords(56, 28)[1]);
        for (Text t : townText) {
            t.render(g);
        }
    }

    private enum Standing {
        NEUTRAL, GOOD, BAD, VERYGOOD, VERYBAD
    }

}
