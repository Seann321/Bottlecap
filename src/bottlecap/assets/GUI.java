package bottlecap.assets;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GUI {

    public ArrayList<Text> text = new ArrayList<>();
    public ArrayList<Text> newText= new ArrayList<>();

    public static GUI gui;

    public void render(Graphics g) {
        for (Text t : text) {
            if(t.active)
            t.render(g);
        }
    }

    public void tick() {
        text.clear();
        text.addAll(newText);
        for (Text t : text) {
            if(t.active)
            t.tick();
        }
    }

    public void onMouseMove(MouseEvent e) {
        for (Text o : newText) {
            o.onMouseMove(e);
        }
    }

    public void onMouseRelease(MouseEvent e) {
        for (Text o : newText) {
            o.onMouseReleased(e);
        }
    }

    public void addText(Text e) {
        newText.add(e);
    }

    public void removeText(Text e) {
        newText.remove(e);
    }

}
