package bottlecap.states.voidstate.tiles;

import bottlecap.states.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

public class TileManager {


    private ArrayList<TileEntities> tileEntities = new ArrayList<>();
    private ArrayList<TileEntities> newTileEntities = new ArrayList<>();
    private ArrayList<TileEntities> multiplayerEntities = new ArrayList<>();
    private ArrayList<TileEntities> newMultiplayerEntities = new ArrayList<>();
    private Handler handler;
    private final Tiles tiles;
    public Boolean liteUp = false;
    public Boolean debug = true;
    public Boolean multiplayer = false;
    private int playerID = 0;
    private boolean pickAColor = false;
    private static Rectangle playerStartingPOS;
    private static Rectangle truePlayerStartingPOS;

    public TileManager(Handler handler, Tiles tiles) {
        this.handler = handler;
        this.tiles = tiles;
        playerStartingPOS = new Rectangle((tiles.cords(50, 85)[0]) - tiles.xDiv / 2, tiles.cords(50, 85)[1], 20, 30);
        truePlayerStartingPOS = new Rectangle((tiles.cords(50, 85)[0]) - tiles.xDiv / 2, tiles.cords(50, 85)[1], 20, 30);
        newTileEntities.add(new Player(playerStartingPOS, handler));
        createCandleWalkway();
        newTileEntities.add(new Candle(tiles.cords(50, 10), true, handler));
        createDoorways();
        createPlayerSlots();
        for (TileEntities p : tileEntities) {
            if (p instanceof Player) {
                playerID = p.hashCode();
            }
        }
        handler.computerID = playerID;
        if (debug) {
            startMutliplayer("192.168.0.15");
        }

    }


    public void tick() {
        multiplayerEntities.clear();
        multiplayerEntities.addAll(newMultiplayerEntities);
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
            collisionwithCharacterSelections();
        }
        multiplayerTick();
        commands();
    }

    public void commands() {
        if (handler.getKM().keyJustPressed(KeyEvent.VK_F5)) {
            newMultiplayerEntities.clear();
        }
    }

    private int testX = 0, testY = 0;
    private String lastMessageSent = "";

    public void multiplayerTick() {
        if (multiplayer) {
            String currentMessage = handler.recieveMessage();
            int x = 0;
            int y = 0;
            for (TileEntities p : tileEntities) {
                if (p instanceof Player) {
                    //Needs to return the TILE
                    x = tiles.tilePOS(p.cords[0], p.cords[1])[0];
                    y = tiles.tilePOS(p.cords[0], p.cords[1])[1];
                    //System.out.println("X " + x);
                    //System.out.println("Y " + y);

                    if (!("CORDS" + x + "" + y + "" + playerID).equals(lastMessageSent)) {
                        handler.sendMessage("CORDSX" + x + "Y" + y + "ID");
                        lastMessageSent = ("CORDSX" + x + "Y" + y + "ID");
                        //System.out.println("CORDS: X " + x + " Y " + y + " ID " + playerID);
                        //System.out.println("Last " + lastMessageSent);
                    }
                }
            }
            if (currentMessage.startsWith("COLORCHANGE")) {
                if (currentMessage.contains("" + playerID))
                    return;
                //System.out.println(lastMessageSent);
                Color tempColor = colorConvertor(currentMessage.substring(29, 42));
                TileEntities temp = grabByID(Integer.parseInt(currentMessage.substring(43)));
                for (TileEntities play : multiplayerEntities) {
                    if (play.privateID == temp.privateID) {
                        {
                            ((Player) play).setColor(tempColor);
                            System.out.println(((Player) play).privateID + " Changed to Color: " + tempColor + " From " + ((Player) play).getColor());
                        }
                    }
                    return;

                }
            }
            if (currentMessage.startsWith("CORDS")) {
                if (!currentMessage.contains("" + playerID)) {
                    int localX = currentMessage.indexOf("X");
                    int localY = currentMessage.indexOf("Y");
                    int localID = currentMessage.indexOf("ID");
                    int newID = Integer.parseInt(currentMessage.substring(localID + 2));
                    int newX = Integer.parseInt(currentMessage.substring(localX + 1, localY));
                    int newY = Integer.parseInt(currentMessage.substring(localY + 1, localID));
                    testX = tiles.cords(newX, newY)[0];
                    testY = tiles.cords(newX, newY)[1];

                    //TestCord Info
                    //System.out.println(currentMessage);
                    //System.out.println("Incoming X: " + newX);
                    //System.out.println("Incoming Y: " + newY);
                    //System.out.println("New X: " + testX);
                    //System.out.println("New Y: " + testY);


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
                        return;
                    }
                }

                //System.out.println(handler.lastMessage);
                //System.out.println("X:" + handler.lastMessage.substring(5,7));
                //System.out.println("Y:" + handler.lastMessage.substring(7,9));
                //System.out.println("ID:" + handler.lastMessage.substring(9,handler.lastMessage.length()-1));
            }


        }
    }


    public Color colorConvertor(String colorStart) {
        int a = Integer.parseInt(colorStart.substring(0, 2));
        int b = Integer.parseInt(colorStart.substring(5, 7));
        int c = Integer.parseInt(colorStart.substring(10, 12));
        return new Color(a, b, c);
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
                    //System.out.println(((Player) p).privateID);
                    return true;
            }
        }
        return false;
    }

    public void startSinglePlayer() {
        handler.setCurrentState(handler.creationState);
    }

    public void startMutliplayer() {
        for (TileEntities p : tileEntities) {
            if (p instanceof Player) {
                ((Player) p).setPlayerPOS(truePlayerStartingPOS.x, truePlayerStartingPOS.y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        handler.client.connectToServer();
        handler.sendMessage("1");
        multiplayer = true;

    }

    public void startMutliplayer(String IP) {
        for (TileEntities p : tileEntities) {
            if (p instanceof Player) {
                ((Player) p).setPlayerPOS(truePlayerStartingPOS.x, truePlayerStartingPOS.y);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        handler.client.connectToServer(IP);
        handler.sendMessage("1");
        multiplayer = true;

    }

    public void createPlayerSlots() {
        //Singleplayer Slots
        newTileEntities.add(new CharacterSlots(tiles.cords(25, 50), handler));
        newTileEntities.add(new CharacterSlots(tiles.cords(25, 60), handler));
        newTileEntities.add(new CharacterSlots(tiles.cords(35, 50), handler));
        newTileEntities.add(new CharacterSlots(tiles.cords(35, 60), handler));
        //Multiplayer Slots
        newTileEntities.add(new CharacterSlots(tiles.cords(65, 50), handler));
        newTileEntities.add(new CharacterSlots(tiles.cords(65, 60), handler));
        newTileEntities.add(new CharacterSlots(tiles.cords(75, 50), handler));
        newTileEntities.add(new CharacterSlots(tiles.cords(75, 60), handler));
    }

    public void collisionwithCharacterSelections() {
        for (TileEntities c : tileEntities) {
            if (c instanceof CharacterSlots) {
                for (TileEntities p : tileEntities) {
                    if (p instanceof Player) {
                        if (((CharacterSlots) c).bounds.intersects(((Player) p).getBounds())) {
                            if (((Player) p).privateID != playerID) {
                                return;
                            }
                            ((Player) p).setColor(((CharacterSlots) c).color);
                            pickAColor = false;
                            for (TileEntities cc : tileEntities) {
                                if (cc instanceof CharacterSlots) {
                                    ((CharacterSlots) cc).selected = false;
                                }
                            }
                            ((CharacterSlots) c).selected = true;
                        }
                    }
                }
            }
        }
    }

    public void collisionWithDoors() {
        for (TileEntities d : tileEntities) {
            if (d instanceof Doorway) {
                for (TileEntities p : tileEntities) {
                    if (p instanceof Player) {
                        if (((Doorway) d).bounds.intersects(((Player) p).getBounds())) {
                            if (((Player) p).getColor() == Color.gray) {
                                pickAColor = true;
                            } else if (((Doorway) d).singlePlayer) {
                                startSinglePlayer();
                            } else if (!((Doorway) d).singlePlayer) {
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

        if (debug) {
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

        //g.setColor(Color.white);
        //g.drawRect(testX, testY, 50, 50);

        Iterator<TileEntities> it = tileEntities.iterator();
        while (it.hasNext()) {
            TileEntities x = it.next();
            if (x.liteUp)
                x.render(g);
        }
        if (pickAColor) {
            g.setColor(Color.yellow);
            g.drawString("Pick a Character Color to continue.", tiles.cords(45, 35)[0], tiles.cords(45, 35)[1]);
        }

        //Multiplayer Rendering
        Iterator<TileEntities> mi = multiplayerEntities.iterator();
        while (mi.hasNext()) {
            TileEntities m = mi.next();
            if (m.liteUp)
                m.render(g);
        }
    }

}
