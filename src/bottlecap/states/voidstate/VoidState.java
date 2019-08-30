package bottlecap.states.voidstate;

import bottlecap.assets.Text;
import bottlecap.states.Handler;
import bottlecap.states.State;
import bottlecap.states.voidstate.tiles.Player;
import bottlecap.states.voidstate.tiles.TileEntities;
import bottlecap.states.voidstate.tiles.TileManager;
import bottlecap.states.Tiles;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class VoidState extends State {

    private Tiles tiles;
    private TileManager tm;
    private ArrayList<Text> textStringsSingle = new ArrayList<>();
    private ArrayList<Text> textStringsMulti = new ArrayList<>();
    private ArrayList<TileEntities> multiplayerEntities = new ArrayList<>();
    private ArrayList<TileEntities> newMultiplayerEntities = new ArrayList<>();
    String currentMessage = handler.recieveMessage();
    private int testX = 0, testY = 0;

    public VoidState(Handler handler) {
        super(handler);
        tiles = new Tiles(handler);
        tm = new TileManager(handler, tiles);
        textStringsSingle.add(new Text("Multiplayer", tiles.cords(67, 4)[0], tiles.cords(67, 4)[1], Text.sFont));
        textStringsSingle.add(new Text("Single Player", tiles.cords(27, 4)[0], tiles.cords(27, 4)[1], Text.sFont));
        textStringsMulti.add(new Text("Start Game", tiles.cords(28, 4)[0], tiles.cords(28, 4)[1], Text.sFont));
        textStringsMulti.add(new Text("Exit Session", tiles.cords(67, 4)[0], tiles.cords(67, 4)[1], Text.sFont));
    }

    @Override
    public void tick() {
        if (!tm.multiplayer) {
            newMultiplayerEntities.clear();

        }
        multiplayerEntities.clear();
        multiplayerEntities.addAll(newMultiplayerEntities);
        tiles.tick();
        tm.tick();
        handler.client.tick();
        commands();
        if (tm.multiplayer)
            recieveMessages();
    }

    public void recieveMessages() {
        currentMessage = handler.recieveMessage();
        if(currentMessage.startsWith("REQUESTCOLOR")){
            if(tm.player() instanceof  Player){
                ((Player) tm.player()).initialColor();
            }
        }
        if (currentMessage.startsWith("COLORCHANGE")) {
            Color tempColor = colorConvertor(currentMessage.substring(29, 42));
            int localID = currentMessage.indexOf("ID");
            TileEntities temp = grabByID(Integer.parseInt(currentMessage.substring(localID + 2)));
            ((Player) temp).setColor(tempColor);
            System.out.println(temp.privateID + " Changed to Color: " + tempColor + " From " + ((Player) temp).getColor());
        }

        if (currentMessage.startsWith("DISCONNECT")) {
            int localID = currentMessage.indexOf("ID");
            int newID = Integer.parseInt(currentMessage.substring(localID + 2));
            for (TileEntities rr : multiplayerEntities) {
                if (rr instanceof Player) {
                    if (((Player) rr).privateID == newID) {
                        newMultiplayerEntities.remove(rr);
                    }
                }
            }
        }

        if (currentMessage.startsWith("CORDS")) {
            int localX = currentMessage.indexOf("X");
            int localY = currentMessage.indexOf("Y");
            int localID = currentMessage.indexOf("ID");
            int newID = Integer.parseInt(currentMessage.substring(localID + 2));
            int newX = Integer.parseInt(currentMessage.substring(localX + 1, localY));
            int newY = Integer.parseInt(currentMessage.substring(localY + 1, localID));
            testX = tiles.cords(newX, newY)[0];
            testY = tiles.cords(newX, newY)[1];

            if (isIDBeingUsed(newID)) {
                for (TileEntities rr : multiplayerEntities) {
                    if (rr instanceof Player) {
                        if (((Player) rr).privateID == newID) {
                            ((Player) rr).setPlayerPOS(testX, testY);
                        }
                    }
                }
            } else {
                System.out.println("New Char made with ID of: " + newID);
                newMultiplayerEntities.add(new Player(new Rectangle(testX, testY, 20, 30), handler, newID));
                handler.client.sendMessage("REQUESTCOLOR");
            }
        }

    }

    TileEntities grabByID(int ID) {
        for (TileEntities p : multiplayerEntities) {
            if (p.privateID == ID)
                return p;
        }
        return null;
    }

    boolean isIDBeingUsed(int ID) {
        for (TileEntities p : multiplayerEntities) {
            if (p instanceof Player) {
                if (ID == ((Player) p).privateID)
                    return true;
            }
        }
        return false;
    }

    public Color colorConvertor(String colorStart) {
        int a = Integer.parseInt(colorStart.substring(0, 2));
        int b = Integer.parseInt(colorStart.substring(5, 7));
        int c = Integer.parseInt(colorStart.substring(10, 12));
        return new Color(a, b, c);
    }


    public void commands() {
        if (handler.getKM().keyJustPressed(KeyEvent.VK_F5)) {
            newMultiplayerEntities.clear();
        }
        if(handler.getKM().keyJustPressed(KeyEvent.VK_BACK_SLASH)){
            handler.setCurrentState(handler.squareState);
        }
    }

    @Override
    public void render(Graphics g) {
        tiles.render(g);
        tm.render(g);
        g.setColor(Color.YELLOW);
        if (tm.liteUp && !tm.multiplayer) {
            for (Text t : textStringsSingle) {
                t.render(g);
            }
        }
        if (tm.liteUp && tm.multiplayer) {
            for (Text t : textStringsMulti) {
                t.render(g);
            }
        }
        for (TileEntities multiplayerEntity : multiplayerEntities) {
            if (multiplayerEntity.liteUp) {
                multiplayerEntity.render(g);
            }
        }
    }
}
