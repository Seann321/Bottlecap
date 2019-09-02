package bottlecap.states.gameState;

import bottlecap.assets.GUI;
import bottlecap.assets.Text;
import bottlecap.states.Handler;
import bottlecap.states.State;
import bottlecap.states.Tiles;
import bottlecap.states.voidstate.tiles.CharacterSlots;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameState extends State {

    private Text[] uiInfo;
    private boolean debug = true;
    Tiles tiles;
    private GUI gui;

    public GameState(Handler handler) {
        super(handler);
        gui = new GUI();
        tiles = new Tiles(handler);
        uiInfo = new Text[]{
                new Text("Health: ", tiles.cords(1, 92), Text.mFont, false, Color.white, false),
                new Text("Level: ", tiles.cords(1, 94), Text.mFont, false, Color.white, false),
                new Text("", tiles.cords(1, 98), Text.mFont, false, Color.white, false)
        };
        for (Text t : uiInfo) {
            gui.addText(t);
        }
    }

    @Override
    public void tick() {
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
            if(uiInfo[0].wasClicked()){
                System.out.println(true);
            }
            if (handler.getMM().isRightPressed()) {
                System.out.println("X " + tiles.tilePOS(handler.getMM().getMouseX(), handler.getMM().getMouseY())[0] + " Y " + tiles.tilePOS(handler.getMM().getMouseX(), handler.getMM().getMouseY())[1]);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(0, tiles.cords(0, 90)[1], handler.getWidth(), handler.getHeight());
        gui.render(g);
        tiles.render(g);
    }
}
