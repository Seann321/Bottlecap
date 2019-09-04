package bottlecap.states.voidstate;

import bottlecap.assets.GUI;
import bottlecap.assets.Text;
import bottlecap.states.Handler;
import bottlecap.states.State;
import bottlecap.states.Tiles;
import bottlecap.states.voidstate.tiles.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class VoidState extends State {

    private Color lastRequestColor;
    private String titleRequestResolt = "";
    private Tiles tiles;
    private TileManager tm;
    private ArrayList<Text> textStringsSingle = new ArrayList<>();
    private ArrayList<Text> textStringsMulti = new ArrayList<>();
    private ArrayList<TileEntities> multiplayerEntities = new ArrayList<>();
    private ArrayList<TileEntities> newMultiplayerEntities = new ArrayList<>();
    private Text titleInfo;
    String currentMessage = handler.recieveMessage();
    private int testX = 0, testY = 0;

    public VoidState(Handler handler) {
        super(handler);
        gui = new GUI();
        tiles = new Tiles(handler);
        tm = new TileManager(handler, tiles, gui);
        int centerOfDoorS = 0;
        int centerOfDoorM = 0;
        for(TileEntities t : tm.newTileEntities){
            if(t instanceof  Doorway){
                if(((Doorway) t).singlePlayer){
                    centerOfDoorS = ((Doorway) t).bounds.x + ((Doorway) t).bounds.width/2;
                } else{
                    centerOfDoorM = ((Doorway) t).bounds.x + ((Doorway) t).bounds.width/2;
                }
            }
        }
        titleInfo = new Text("",new int[]{0,0},Text.sFont,false,Color.YELLOW);
        textStringsSingle.add(new Text("Multiplayer",new int[]{centerOfDoorM,tiles.cords(69, 4)[1]}, Text.sFont,true,Color.YELLOW));
        textStringsSingle.add(new Text("Multiplayer", new int[]{centerOfDoorM,tiles.cords(69, 4)[1]}, Text.sFont,true,Color.YELLOW));
        textStringsSingle.add(new Text("Single Player", new int[]{centerOfDoorS, tiles.cords(29, 4)[1]}, Text.sFont,true,Color.YELLOW));
        textStringsMulti.add(new Text("Start Game", new int[]{centerOfDoorS, tiles.cords(29, 4)[1]}, Text.sFont,true,Color.YELLOW));
        textStringsMulti.add(new Text("Exit Session", new int[]{centerOfDoorM, tiles.cords(69, 4)[1]}, Text.sFont,true,Color.YELLOW));
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
        for(TileEntities t : multiplayerEntities){
            if(t instanceof  Player && ((Player) t).getColor() == Color.GRAY){
                handler.sendMessage("REQUESTCOLOR");
            }
        }
    }

    public void recieveMessages() {
        currentMessage = handler.recieveMessage();
        if(currentMessage.startsWith("TITLEINFO")){
            titleRequestResolt = currentMessage.substring(9,currentMessage.indexOf("ID"));
            titleInfo.setText(titleRequestResolt);
        }
        if(currentMessage.startsWith("REQUESTCOLOR")){
            if(tm.player() instanceof  Player){
                ((Player) tm.player()).initialColor();
            }
        }if(currentMessage.startsWith("TITLEREQUEST")){
            handler.sendMessage("TITLEINFO" + (((CharacterSlots) tm.activeChar).nickName) + " LVL: " + ((CharacterSlots) tm.activeChar).level);
        }
        if (currentMessage.startsWith("COLORCHANGE")) {
            //System.out.println(currentMessage.substring(29,42));
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
                handler.sendMessage("REQUESTCOLOR");
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
        if(handler.getKM().keyJustPressed(KeyEvent.VK_F6)){
            //handler.activePlayer = tm.
            handler.setCurrentState(handler.gameState);
        }
        if(handler.getKM().keyJustPressed(KeyEvent.VK_F8)){
            handler.setCurrentState(handler.emptyState);
        }
        if (handler.getKM().keyJustPressed(KeyEvent.VK_F5)) {
            newMultiplayerEntities.clear();
        }
        if(handler.getKM().keyJustPressed(KeyEvent.VK_BACK_SLASH)){
            handler.setCurrentState(handler.squareState);
        }
        if(handler.getKM().keyJustPressed(KeyEvent.VK_F12)){
            handler.fileSystem.deleteSave();
        }
        for(TileEntities t : multiplayerEntities){
            if(t instanceof Player){
                if (((Player) t).getBounds().contains(handler.getMM().getMouseX(),handler.getMM().getMouseY())){
                    if(!(((Player) t).getColor() == lastRequestColor)){
                    handler.sendMessage("TITLEREQUEST");
                        lastRequestColor = ((Player) t).getColor();
                    }
                    titleInfo.setX(handler.getMM().getMouseX() - 10);
                    titleInfo.setY(handler.getMM().getMouseY() - 10);
                }else{
                    titleInfo.setX(10000);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        tiles.render(g);
        tm.render(g);
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
        titleInfo.render(g);
    }
}
