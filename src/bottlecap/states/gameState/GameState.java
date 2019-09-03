package bottlecap.states.gameState;

import bottlecap.assets.GUI;
import bottlecap.assets.Text;
import bottlecap.assets.images.Images;
import bottlecap.states.Handler;
import bottlecap.states.State;
import bottlecap.states.Tiles;
import bottlecap.states.gameState.worldGenerator.WorldGenerator;
import bottlecap.states.voidstate.tiles.CharacterSlots;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class GameState extends State {

    private Text[] uiInfo;
    private boolean debug = true;
    private Tiles tiles;
    private Tiles gridPlacement;
    private WorldGenerator worldGen;

    public GameState(Handler handler) {
        super(handler);
        gui = new GUI();
        tiles = new Tiles(handler);
        gridPlacement = new Tiles(handler,64,36);
        worldGen = new WorldGenerator(handler, gridPlacement, "src/bottlecap/assets/worlds/OverWorld.txt");
        uiInfo = new Text[]{
                new Text("Health: ", tiles.cords(1, 91), Text.mFont, false, Color.white, false),
                new Text("Level: ", tiles.cords(1, 93), Text.mFont, false, Color.white, false),
                new Text("", tiles.cords(1, 97), Text.mFont, false, Color.white, false)
        };
        for (Text t : uiInfo) {
            gui.addText(t);
        }
    }

    @Override
    public void tick() {
        worldGen.tick();
        if (GUI.gui != gui)
            GUI.gui = gui;
        gui.tick();
        debug();
        if (handler.getKM().keyJustPressed(KeyEvent.VK_ENTER))
            nextTurn();
    }

    //((CharacterSlots) handler.activePlayer).level;

    public void nextTurn() {
        uiInfo[0].setText("Health: " + ((CharacterSlots) handler.activePlayer).health);
        uiInfo[1].setText("Level: " + ((CharacterSlots) handler.activePlayer).level);
        uiInfo[2].setText("" + ((CharacterSlots) handler.activePlayer).nickName);
    }

    public void debug() {
        if (debug) {
            if (handler.getMM().isRightPressed()) {
                System.out.println("X " + gridPlacement.tilePOS(handler.getMM().getMouseX(), handler.getMM().getMouseY())[0] + " Y " + gridPlacement.tilePOS(handler.getMM().getMouseX(), handler.getMM().getMouseY())[1]);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(0, gridPlacement.cords(0, 32)[1], handler.getWidth(), handler.getHeight());
        worldGen.render(g);
        gui.render(g);
        gridPlacement.render(g);
    }
}
