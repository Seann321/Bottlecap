package bottlecap.states.gameState;

import bottlecap.states.Handler;
import bottlecap.states.Tiles;
import bottlecap.states.gameState.worldGenerator.WorldGenerator;
import bottlecap.states.gameState.worldGenerator.WorldTiles;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    public String currentWorld;
    public int AP = 3;
    public int startAP = 3;
    public Color color;
    private Handler handler;
    public int privateID;
    public Rectangle bounds;
    private Tiles tiles;
    int[] cancelMove;
    WorldGenerator wg;

    public Player(int[] cords, Color color, Tiles tiles, Handler handler, int privateID) {
        this.tiles = tiles;
        this.color = color;
        wg = GameState.ActiveWorld;
        this.handler = handler;
        this.privateID = privateID;
        bounds = new Rectangle(cords[0], cords[1], tiles.xDiv / 3, tiles.yDiv / 2);
        cancelMove = new int[]{bounds.x, bounds.y};
    }

    public int[] getTileUnderPlayer(){
        WorldTiles temp = wg.returnTileUnderCords(new int[]{bounds.x, bounds.y});
        if(temp == null){
            System.out.println(true);
        }
        return temp.getTileCords();
    }

    public void tick() {
        movement();
    }

    private int movementPoints = 0;

    public void endTurn() {
        movementPoints = 0;
        cancelMove = new int[]{bounds.x, bounds.y};
        if (wg.returnTileType(new int[]{bounds.x, bounds.y}) == WorldTiles.TileType.DOCK) {
            if (GameState.ActiveWorld == GameState.Overworld) {
                GameState.ActiveWorld = GameState.Seantopia;
            } else {
                GameState.ActiveWorld = GameState.Overworld;
            }
            wg = GameState.ActiveWorld;
            for (WorldTiles wt : wg.worldTiles) {
                if (wt.tileType == WorldTiles.TileType.DOCK) {
                    bounds.x = wt.x;
                    bounds.y = wt.y;
                    return;
                }
            }
        }
    }

    public void movement() {

        if (handler.getMM().isRightPressed()) {
            AP += movementPoints;
            movementPoints = 0;
            bounds.x = cancelMove[0];
            bounds.y = cancelMove[1];
        }
        if (AP <= 0) {
            return;
        }
        if (handler.getKM().keyJustPressed(KeyEvent.VK_W)) {
            if (bounds.y - tiles.yDiv >= 0 && (wg.returnTileType(new int[]{bounds.x, bounds.y - tiles.yDiv})) != (WorldTiles.TileType.WATER)) {
                bounds.y -= tiles.yDiv;
                afterPress();
            }
        }
        if (handler.getKM().keyJustPressed(KeyEvent.VK_S)) {
            if (bounds.y + tiles.yDiv <= tiles.cords(0, 31)[1] && (wg.returnTileType(new int[]{bounds.x, bounds.y + tiles.yDiv})) != (WorldTiles.TileType.WATER)) {
                bounds.y += tiles.yDiv;
                afterPress();
            }
        }
        if (handler.getKM().keyJustPressed(KeyEvent.VK_A)) {
            if (bounds.x - tiles.xDiv >= 0 && (wg.returnTileType(new int[]{bounds.x - tiles.xDiv, bounds.y})) != (WorldTiles.TileType.WATER)) {
                bounds.x -= tiles.xDiv;
                afterPress();
            }
        }
        if (handler.getKM().keyJustPressed(KeyEvent.VK_D)) {
            if (bounds.x <= tiles.cords(63, 0)[0] && (wg.returnTileType(new int[]{bounds.x + tiles.xDiv, bounds.y})) != (WorldTiles.TileType.WATER)) {
                bounds.x += tiles.xDiv;
                afterPress();
            }
        }
    }

    public void afterPress() {
        movementPoints++;
        AP--;
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(bounds.x + (tiles.xDiv / 2), bounds.y + (tiles.yDiv / 2), bounds.width, bounds.height);

    }
}
