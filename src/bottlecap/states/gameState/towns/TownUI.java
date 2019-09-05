package bottlecap.states.gameState.towns;

import bottlecap.assets.GUI;
import bottlecap.assets.Text;
import bottlecap.states.Handler;
import bottlecap.states.Tiles;
import bottlecap.states.gameState.GameState;
import bottlecap.states.gameState.worldGenerator.WorldTiles;
import bottlecap.states.voidstate.tiles.CharacterSlots;
import bottlecap.states.voidstate.tiles.TileManager;

import java.awt.*;

public class TownUI {

    private int ID;
    private Tiles tiles;
    private Handler handler;
    private Standing standing;
    public GUI gui;
    public boolean fastTravelMode = false;
    private Text fastTravelText;

    public TownUI(Handler handler, Tiles tiles, int ID) {
        standing = Standing.NEUTRAL;
        this.handler = handler;
        this.tiles = tiles;
        this.ID = ID;
        fastTravelText = new Text("Click which town you'd like to travel to", tiles.cords(32, 3), Text.lFont, true, Color.white, false);
        gui = new GUI();
        gui.addText(new Text("Welcome to the Town of " + ID, tiles.cords(32, 5), Text.lFont, true, Color.white, false));
        gui.addText(new Text("Current Standing: " + standing, tiles.cords(32, 6), Text.sFont, true, Color.white, false));
        gui.addText(new Text("Fast Travel", tiles.cords(32, 8), Text.mFont, true, Color.gray, false));
        gui.addText(new Text("(Must have unlocked skill)", tiles.cords(32, 9), Text.sFont, true, Color.gray, false));
        gui.addText(new Text("Quest", tiles.cords(32, 10), Text.mFont, true, Color.white, true));
        gui.addText(new Text("Shops", tiles.cords(32, 12), Text.mFont, true, Color.white, true));
        gui.addText(new Text("Town Hall", tiles.cords(32, 14), Text.mFont, true, Color.white, true));
        gui.addText(new Text("Enter Town", tiles.cords(32, 31), Text.lFont, true, Color.white, true));
    }

    public void tick() {
        if ((((CharacterSlots) handler.activePlayer).fastTravelUnlocked || TileManager.debug)) {
            for (Text t : gui.text) {
                if (t.getText().equals("Fast Travel")) {
                    t.clickable = true;
                    t.originalColor = Color.white;
                    gui.text.get(3).setText("(Must have full AP)");
                }
            }
        }
        gui.tick();
        wasTextClicked();
        if (fastTravelMode) {
            fastTravel();
        }
    }

    public void fastTravel() {
        if (GameState.Player.startAP != GameState.Player.AP) {
            fastTravelMode = false;
        }
        for (WorldTiles wt : GameState.ActiveWorld.worldTiles) {
            if (handler.getMM().isLeftPressed())
                if (wt.bounds.contains(handler.getMM().getMouseX(), handler.getMM().getMouseY()) && (wt.tileType == WorldTiles.TileType.GRASSTOWN || wt.tileType == WorldTiles.TileType.DESSERTTOWN)) {
                    fastTravelMode = false;
                    GameState.Player.bounds.x = wt.x;
                    GameState.Player.bounds.y = wt.y;
                    GameState.Player.AP = 0;
                } else {
                    fastTravelMode = false;
                }
        }
    }

    public void wasTextClicked() {
        for (Text t : gui.text) {
            if (t.getText().equals("Fast Travel")) {
                if (t.wasClicked() && GameState.Player.AP == GameState.Player.startAP) {
                    fastTravelMode = true;
                }
            }
        }
    }

    public void render(Graphics g) {
        if (!fastTravelMode) {
            g.setColor(new Color(10, 10, 10, 100));
            g.fillRect(tiles.cords(4, 4)[0], tiles.cords(4, 4)[1], tiles.cords(56, 28)[0], tiles.cords(56, 28)[1]);
            gui.render(g);
        }
        if (fastTravelMode) {
            fastTravelText.render(g);
        }
    }

    private enum Standing {
        NEUTRAL, GOOD, BAD, VERYGOOD, VERYBAD
    }

}
