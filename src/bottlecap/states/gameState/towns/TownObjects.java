package bottlecap.states.gameState.towns;

import java.awt.*;
import java.util.ArrayList;

public class TownObjects {

    public int ID = -1;
    public Rectangle bounds;
    public String worldName;
    private ArrayList<Rectangle> townBoundries = new ArrayList<>();

    public TownObjects(int ID, Rectangle bounds, String worldName) {
        this.ID = ID;
        this.worldName = worldName;
        this.bounds = bounds;
    }

}
