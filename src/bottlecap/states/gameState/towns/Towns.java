package bottlecap.states.gameState.towns;

import bottlecap.states.Handler;
import bottlecap.states.Tiles;
import bottlecap.states.gameState.GameState;
import bottlecap.states.gameState.worldGenerator.WorldGenerator;
import bottlecap.states.gameState.worldGenerator.WorldTiles;

import java.awt.*;
import java.util.ArrayList;

public class Towns {

    private Tiles tiles;
    int i = 0;
    private Handler handler;
    private ArrayList<TownObjects> townsOverWorld = new ArrayList<>();
    private ArrayList<TownObjects> townsSeantopia = new ArrayList<>();

    public Towns(Handler handler, Tiles tiles) {
        this.tiles = tiles;
        this.handler = handler;
        generateTowns();
    }

    public TownObjects grabTownByID(int ID) {
        for (TownObjects to : townsOverWorld) {
            if (to.ID == ID) {
                return to;
            }
        }
        for (TownObjects to : townsSeantopia) {
            if (to.ID == ID) {
                return to;
            }
        }
        return null;
    }

    public int grabTownID(int[] cords) {
        if (GameState.ActiveWorld == GameState.Overworld) {
            for (TownObjects to : townsOverWorld) {
                if (to.bounds.x == cords[0] && to.bounds.y == cords[1]) {
                    return to.ID;
                }
            }
        }
        if (GameState.ActiveWorld == GameState.Seantopia) {
            for (TownObjects to : townsSeantopia) {
                if (to.bounds.x == cords[0] && to.bounds.y == cords[1]) {
                    return to.ID;
                }
            }
        }
        return -1;
    }

    public void generateTowns() {
        startTheForLoop(GameState.Overworld, townsOverWorld);
        startTheForLoop(GameState.Seantopia, townsSeantopia);
        System.out.println("Towns Made: " + i);
    }

    public void startTheForLoop(WorldGenerator wg, ArrayList<TownObjects> towns) {
        for (WorldTiles wt : wg.worldTiles) {
            if (wt.tileType == WorldTiles.TileType.GRASSTOWN || wt.tileType == WorldTiles.TileType.DESSERTTOWN) {
                if (townAlreadyGivenValue(wt.x, wt.y, wg.worldTitle, towns))
                    continue;
                towns.add(new TownObjects(handler, tiles, i, new Rectangle(wt.x, wt.y, tiles.xDiv, tiles.yDiv), wg.worldTitle));
                checkNearbyTiles(wt, wg.worldTitle, towns, wg);
                i++;
            }
        }
    }

    public boolean townAlreadyGivenValue(int x, int y, String worldName, ArrayList<TownObjects> towns) {
        for (TownObjects t : towns) {
            if (!t.worldName.equals(worldName)) {
                continue;
            }
            if (t.ID != -1 && t.bounds.x == x && t.bounds.y == y) {
                return true;
            }
        }
        return false;
    }

    public void checkNearbyTiles(WorldTiles startingTile, String worldName, ArrayList<TownObjects> towns, WorldGenerator worldTiles) {
        for (WorldTiles wt : worldTiles.worldTiles) {
            if (wt.tileType == WorldTiles.TileType.GRASSTOWN || wt.tileType == WorldTiles.TileType.DESSERTTOWN) {
                if (wt.x >= startingTile.x - tiles.xDiv && wt.x <= startingTile.x + tiles.xDiv) {
                    if (wt.y >= startingTile.y - tiles.yDiv && wt.y <= startingTile.y + tiles.yDiv) {
                        if (!townAlreadyGivenValue(wt.x, wt.y, worldName, towns)) {
                            towns.add(new TownObjects(handler, tiles, i, new Rectangle(wt.x, wt.y, tiles.xDiv, tiles.yDiv), worldName));
                            checkNearbyTiles(wt, worldName, towns, worldTiles);
                        }
                    }
                }
            }
        }
    }

    public void tick() {

    }

    public void render(Graphics g) {
        //renderArrayList(g,townsOverWorld);
        //renderArrayList(g,townsSeantopia);
    }

    public void renderArrayList(Graphics g, ArrayList<TownObjects> towns) {
        for (TownObjects to : towns) {
            if (!to.worldName.equals(GameState.ActiveWorld.worldTitle)) {
                continue;
            }
            switch (to.ID) {
                case 0:
                    g.setColor(Color.red);
                    break;
                case 1:
                    g.setColor(Color.blue);
                    break;
                case 2:
                    g.setColor(Color.green);
                    break;
                case 3:
                    g.setColor(Color.black);
                    break;
                case 4:
                    g.setColor(Color.gray);
                    break;
                case 5:
                    g.setColor(Color.cyan);
                    break;
                case 6:
                    g.setColor(Color.white);
                    break;
                case 7:
                    g.setColor(Color.pink);
                    break;
                case 8:
                    g.setColor(Color.magenta);
                    break;
                case 9:
                    g.setColor(Color.LIGHT_GRAY);
                    break;
                case 10:
                    g.setColor(Color.darkGray);
                    break;
            }
            g.drawRect(to.bounds.x, to.bounds.y, to.bounds.width, to.bounds.height);
        }
    }

}
