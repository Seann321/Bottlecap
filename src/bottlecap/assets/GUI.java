package bottlecap.assets;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GUI {

    public ArrayList<Text> text = new ArrayList<>();

    public static GUI gui;

    public void render(Graphics g) {
        for (Text t : text) {
            if(t.active)
            t.render(g);
        }
    }

    public void tick() {
        for (Text t : text) {
            if(t.active)
            t.tick();
        }
    }

    public void onMouseMove(MouseEvent e) {
        for (Text o : text) {
            o.onMouseMove(e);
        }
    }

    public void onMouseRelease(MouseEvent e) {
        for (Text o : text) {
            o.onMouseReleased(e);
        }
    }

    public void addText(Text e) {
        text.add(e);
    }

    public void removeText(Text e) {
        text.remove(e);
    }

}
