package bottlecap.states.creationstate;

import java.awt.*;

public class Stat {

    public Rectangle bounds;
    public boolean selected, filled, locked;
    public Color color = Color.gray;
    public int[] startCords;

    public Stat(Rectangle bounds){
        this.bounds = bounds;
        startCords = new int[2];
        startCords[0] = bounds.x;
        startCords[1] = bounds.y;
    }

}
