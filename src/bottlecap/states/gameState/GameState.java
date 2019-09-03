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
    private Tiles gridPlacement;
    private WorldGenerator worldGen;
    private Player player;
    private boolean firstLoad = true;
    private int turnCount = 0;

    public GameState(Handler handler) {
        super(handler);
        gui = new GUI();
        gridPlacement = new Tiles(handler, 64, 36);
        worldGen = new WorldGenerator(handler, gridPlacement, "src/bottlecap/assets/worlds/OverWorld.txt");
        uiInfo = new Text[]{
                new Text("Health: ", gridPlacement.cords(1, 33), Text.mFont, false, Color.white, false),
                new Text("Level: ", gridPlacement.cords(1, 34), Text.mFont, false, Color.white, false),
                new Text("", gridPlacement.cords(1, 35), Text.mFont, false, Color.white, false),
                new Text("AP Remaining: ", gridPlacement.cords(32, 33), Text.lFont, true, Color.white, false),
                new Text("Enter To End Turn; Right Click to Cancel Movement", gridPlacement.cords(32, 34), Text.sFont, true, Color.white, false),
                new Text("Turn: " + 0, gridPlacement.cords(32, 35), Text.sFont, true, Color.white, false)
        };
        for (Text t : uiInfo) {
            gui.addText(t);
        }
    }

    @Override
    public void tick() {
        if (firstLoad) {
            firstLoading();
        }
        player.tick();
        uiInfo[3].setText("AP Remaining: " + (player.AP - player.movementPoints));
        worldGen.tick();
        if (GUI.gui != gui)
            GUI.gui = gui;
        gui.tick();
        debug();
        if (handler.getKM().keyJustPressed(KeyEvent.VK_SPACE))
            nextTurn();
    }

    //((CharacterSlots) handler.activePlayer).level;

    public void firstLoading() {
        player = new Player(gridPlacement.cords(32, 21), ((CharacterSlots) handler.activePlayer).color, gridPlacement, handler,worldGen, handler.computerID);
        System.out.println("Player Created with ID: " + player.privateID);
        uiInfo[0].setText("Health: " + ((CharacterSlots) handler.activePlayer).health);
        uiInfo[1].setText("Level: " + ((CharacterSlots) handler.activePlayer).level);
        uiInfo[2].setText("" + ((CharacterSlots) handler.activePlayer).nickName);
        firstLoad = false;
    }

    public void nextTurn() {
        turnCount++;
        uiInfo[0].setText("Health: " + ((CharacterSlots) handler.activePlayer).health);
        uiInfo[1].setText("Level: " + ((CharacterSlots) handler.activePlayer).level);
        uiInfo[2].setText("" + ((CharacterSlots) handler.activePlayer).nickName);
        uiInfo[5].setText("Turn: " + turnCount);
        player.endTurn();
        player.AP = player.startAP;
    }

    public void debug() {
        if (debug) {


           // if (handler.getMM().isRightPressed()) {
           //     System.out.println("X " + gridPlacement.tilePOS(handler.getMM().getMouseX(), handler.getMM().getMouseY())[0] + " Y " + gridPlacement.tilePOS(handler.getMM().getMouseX(), handler.getMM().getMouseY())[1]);
           // }
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(0, gridPlacement.cords(0, 32)[1], handler.getWidth(), handler.getHeight());
        worldGen.render(g);
        gui.render(g);
        gridPlacement.render(g);
        if (player != null)
            player.render(g);

    }
}
