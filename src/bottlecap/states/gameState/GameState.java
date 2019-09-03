package bottlecap.states.gameState;

import bottlecap.assets.GUI;
import bottlecap.assets.Text;
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
    public static WorldGenerator Overworld;
    public static WorldGenerator Seantopia;
    private Player player;
    private boolean firstLoad = true;
    private int turnCount = 0;
    public static WorldGenerator ActiveWorld;
    private ArrayList<Player> multiplayers;
    private ArrayList<Player> newMultiplayers;

    public GameState(Handler handler) {
        super(handler);
        gui = new GUI();
        gridPlacement = new Tiles(handler, 64, 36);
        Overworld = new WorldGenerator(handler, gridPlacement, "src/bottlecap/assets/worlds/Overworld.txt", "Overworld");
        ActiveWorld = Overworld;
        Seantopia = new WorldGenerator(handler, gridPlacement, "src/bottlecap/assets/worlds/Seantopia.txt", "Seantopia");
        multiplayers = new ArrayList<>();
        newMultiplayers = new ArrayList<>();
        uiInfo = new Text[]{
                new Text("Health: ", gridPlacement.cords(1, 33), Text.mFont, false, Color.white, false),
                new Text("Level: ", gridPlacement.cords(1, 34), Text.mFont, false, Color.white, false),
                new Text("", gridPlacement.cords(1, 35), Text.mFont, false, Color.white, false),
                new Text("AP Remaining: ", gridPlacement.cords(32, 33), Text.lFont, true, Color.white, false),
                new Text("Enter To End Turn; Right Click to Cancel Movement", gridPlacement.cords(32, 34), Text.sFont, true, Color.white, false),
                new Text("Turn: " + 0, gridPlacement.cords(32, 35), Text.sFont, true, Color.white, false),
                new Text("Island: " + ActiveWorld.worldTitle, gridPlacement.cords(64, 34), Text.lFont, Color.white, false, true)
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
        ActiveWorld.tick();
        if (GUI.gui != gui)
            GUI.gui = gui;
        gui.tick();
        for (Player p : multiplayers) {
            //System.out.println("Other X " + gridPlacement.tilePOS(p.bounds.x, p.bounds.y)[0] + " Y " + gridPlacement.tilePOS(p.bounds.x, p.bounds.y)[1]);
            //System.out.println("Player X " +gridPlacement.tilePOS(player.bounds.x, player.bounds.y)[0]  + " Y " + gridPlacement.tilePOS(player.bounds.x, player.bounds.y)[1]);
        }
        if (handler.getKM().keyJustPressed(KeyEvent.VK_ENTER))
            nextTurn();
        if (handler.multiplayer) {
            multiplayers.clear();
            multiplayers.addAll(newMultiplayers);
            handler.client.tick();
            recieveMessages();
        }
    }

    public void multiplayerTick() {
        sendMessages();
    }

    public void nextTurn() {
        turnCount++;
        uiInfo[0].setText("Health: " + ((CharacterSlots) handler.activePlayer).health);
        uiInfo[1].setText("Level: " + ((CharacterSlots) handler.activePlayer).level);
        uiInfo[2].setText("" + ((CharacterSlots) handler.activePlayer).nickName);
        uiInfo[5].setText("Turn: " + turnCount);
        player.endTurn();
        player.AP = player.startAP;
        uiInfo[6].setText("Island: " + ActiveWorld.worldTitle);
        if (handler.multiplayer) {
            multiplayerTick();
        }
    }

    public void sendMessages() {
        handler.sendMessage("PLAYERDATA" + "X" + player.getTileUnderPlayer()[0] + "Y" + player.getTileUnderPlayer()[1]
                + "COLOR" + player.color + "WORLD" + ActiveWorld.worldTitle);
    }

    private String currentMessage = "";

    public void recieveMessages() {
        currentMessage = handler.recieveMessage();
        if (currentMessage.startsWith("PLAYERDATA")) {
            currentMessage = currentMessage.substring(currentMessage.indexOf("PLAYERDATA") + 10);
            int ID = Integer.parseInt(currentMessage.substring(currentMessage.indexOf("ID") + 2));
            int x = Integer.parseInt(currentMessage.substring((currentMessage.indexOf("X") + 1), currentMessage.indexOf("Y")));
            int y = Integer.parseInt(currentMessage.substring((currentMessage.indexOf("Y") + 1), currentMessage.indexOf("COLOR")));
            Color color = colorConvertor(currentMessage.substring(currentMessage.indexOf("COLOR") + 22, currentMessage.indexOf("WORLD") - 1));
            String worldName = currentMessage.substring(currentMessage.indexOf("WORLD") + 5, currentMessage.indexOf("ID"));
            if (multiplayers.size() == 0) {
                newMultiplayers.add(new Player(gridPlacement.cords(x, y), color, gridPlacement, handler, ID));
                System.out.println("New Player made with ID " + ID);
            }
            multiplayers.clear();
            multiplayers.addAll(newMultiplayers);
            for (Player p : multiplayers) {
                if (p.privateID == ID) {
                    p.color = color;
                    p.bounds.x = gridPlacement.cords(x, y)[0];
                    p.bounds.y = gridPlacement.cords(x, y)[1];
                    p.currentWorld = worldName;
                } else {
                    newMultiplayers.add(new Player(gridPlacement.tilePOS(x, y), color, gridPlacement, handler, ID));
                    System.out.println("New Player made with ID " + ID);
                }
            }
        }
    }

    public Color colorConvertor(String colorStart) {
        int a = Integer.parseInt(colorStart.substring(0, 2));
        int b = Integer.parseInt(colorStart.substring(5, 7));
        int c = Integer.parseInt(colorStart.substring(10, 12));
        return new Color(a, b, c);
    }

    //((CharacterSlots) handler.activePlayer).level;

    public void firstLoading() {
        player = new Player(gridPlacement.cords(32, 21), ((CharacterSlots) handler.activePlayer).color, gridPlacement, handler, handler.computerID);
        System.out.println("Player Created with ID: " + player.privateID);
        uiInfo[0].setText("Health: " + ((CharacterSlots) handler.activePlayer).health);
        uiInfo[1].setText("Level: " + ((CharacterSlots) handler.activePlayer).level);
        uiInfo[2].setText("" + ((CharacterSlots) handler.activePlayer).nickName);
        firstLoad = false;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(0, gridPlacement.cords(0, 32)[1], handler.getWidth(), handler.getHeight());
        ActiveWorld.render(g);
        gui.render(g);
        gridPlacement.render(g);
        if (player != null)
            player.render(g);
        for (Player p : multiplayers) {
            if (p.currentWorld.equals(ActiveWorld.worldTitle)) {
                p.render(g);
            }
        }
    }
}
