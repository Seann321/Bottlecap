package bottlecap.states.gameState.worldGenerator;

import bottlecap.assets.GUI;
import bottlecap.assets.Text;
import bottlecap.states.Handler;
import bottlecap.states.State;
import bottlecap.states.Tiles;

import java.awt.*;
import java.awt.event.KeyEvent;

public class EmptyState extends State {

    private Tiles gridPlacement;
    private WorldGenerator worldGen;


    public EmptyState(Handler handler) {
        super(handler);
        gui = new GUI();
        gridPlacement = new Tiles(handler, 64, 36);
        worldGen = new WorldGenerator(handler,gridPlacement);
    }

    @Override
    public void tick() {
        worldGen.debug = true;
        WorldTiles.debug = true;
        if(handler.getKM().keyJustPressed(KeyEvent.VK_R)){
            for(WorldTiles wt : worldGen.worldTiles){
                wt.tileType = WorldTiles.TileType.WATER;
            }
        }
        worldGen.tick();
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(0, gridPlacement.cords(0, 32)[1], handler.getWidth(), handler.getHeight());
        Text text = new Text("Current TileType: " + WorldTiles.activeMapPainter,gridPlacement.cords(32,33),Text.lFont,true,Color.lightGray);
        Text text2 = new Text("0 - Water, 1 - Grass, 2 - Forrest, 3 - Dessert, 4 - Bridge, 5 - DESSERTTOWN, 6- GRASSTOWN, 7 - DOCK",gridPlacement.cords(32,34),Text.sFont,true,Color.lightGray);
        worldGen.render(g);
        text.render(g);
        text2.render(g);
    }
}
