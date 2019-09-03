package bottlecap.states.gameState;

import bottlecap.states.Handler;
import bottlecap.states.Tiles;
import bottlecap.states.gameState.worldGenerator.WorldGenerator;
import bottlecap.states.gameState.worldGenerator.WorldTiles;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {

    public int AP = 3;
    public int startAP = 3;
    private Color color;
    private Handler handler;
    public int privateID;
    private Rectangle bounds;
    private Tiles tiles;
    int[] cancelMove;
    WorldGenerator wg;

    public Player(int[] cords, Color color, Tiles tiles, Handler handler, WorldGenerator worldGen, int privateID) {
        this.tiles = tiles;
        this.color = color;
        wg = worldGen;
        this.handler = handler;
        this.privateID = privateID;
        bounds = new Rectangle(cords[0], cords[1], 10, 15);
        cancelMove = new int[]{bounds.x, bounds.y};
    }

    public void tick() {
        movement();
    }

    public int movementPoints = 0;

    public void endTurn(){
        movementPoints = 0;
        cancelMove = new int[]{bounds.x, bounds.y};
    }

    public void movement() {

        if (handler.getMM().isRightPressed()) {
            movementPoints = 0;
            bounds.x = cancelMove[0];
            bounds.y = cancelMove[1];
        }
        if (handler.getKM().keyJustPressed(KeyEvent.VK_ENTER)) {

        }
        if (movementPoints >= AP) {
            return;
        }
        if (handler.getKM().keyJustPressed(KeyEvent.VK_W)) {
            if (bounds.y - tiles.yDiv >= 0 && (wg.returnTileType(new int[]{bounds.x , bounds.y - (int)tiles.yDiv})) != (WorldTiles.TileType.WATER)) {
                bounds.y -= tiles.yDiv;
                afterPress();
            }
        }
        if (handler.getKM().keyJustPressed(KeyEvent.VK_S)) {
            if (bounds.y + tiles.yDiv <= tiles.cords(0, 31)[1] && (wg.returnTileType(new int[]{bounds.x, bounds.y  + (int)tiles.yDiv})) != (WorldTiles.TileType.WATER)) {
                bounds.y += tiles.yDiv;
                afterPress();
            }
        }
        if (handler.getKM().keyJustPressed(KeyEvent.VK_A)) {
            if (bounds.x - tiles.xDiv >= 0 && (wg.returnTileType(new int[]{bounds.x - (int)tiles.xDiv, bounds.y})) != (WorldTiles.TileType.WATER)) {
                bounds.x -= tiles.xDiv;
                afterPress();
            }
        }
        if (handler.getKM().keyJustPressed(KeyEvent.VK_D)) {
            if (bounds.x <= tiles.cords(63, 0)[0] && (wg.returnTileType(new int[]{bounds.x + (int)tiles.xDiv, bounds.y})) != (WorldTiles.TileType.WATER)) {
                bounds.x += tiles.xDiv;
                afterPress();
            }
        }
    }

    public void afterPress() {
        movementPoints++;
        //System.out.println(wg.returnTileType(new int[]{bounds.x, bounds.y}));
    }

    public void render(Graphics g) {
        g.setColor(color);
        g.fillRect(bounds.x + (int) (tiles.xDiv / 2), bounds.y + (int) (tiles.yDiv / 2), bounds.width, bounds.height);

    }
}
