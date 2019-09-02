package bottlecap.states.creationstate;

import bottlecap.assets.GUI;
import bottlecap.assets.Text;
import bottlecap.states.Handler;
import bottlecap.states.State;
import bottlecap.states.Tiles;

import java.awt.*;
import java.util.ArrayList;

public class CreationState extends State {

    private ArrayList<Text> text = new ArrayList<>();
    private Tiles tiles;
    private int yOffset;
    private GUI gui;
    private boolean activeChange = false;
    Color darkOrange = new Color(255, 140, 0);

    public CreationState(Handler handler) {
        super(handler);
        gui = new GUI();
        tiles = new Tiles(handler);
        yOffset = 30;

        gui.addText(new Text("Character Sheet", tiles.cords(50, 5), Text.lFont, true, Color.lightGray));
        gui.addText(new Text("Strength", tiles.cords(50, 5), Text.lFont, true, Color.red));
        gui.addText(new Text("Magic", tiles.cords(50, 5), Text.lFont, true, Color.blue));
        gui.addText(new Text("Perception", tiles.cords(50, 5), Text.lFont, true, Color.MAGENTA));
        gui.addText(new Text("Charisma", tiles.cords(50, 5), Text.lFont, true, Color.green));
        gui.addText(new Text("Defence", tiles.cords(50, 5), Text.lFont, true, darkOrange));
        gui.addText(new Text("Agility", tiles.cords(50, 5), Text.lFont, true, Color.cyan));

        for (Text t : gui.text) {
            if (t.getText().equals("Character Sheet")) continue;
            t.setY(tiles.cords(40, yOffset)[1]);
            yOffset += 10;
        }
        yOffset = 30;
    }

    @Override
    public void tick() {
        if (GUI.gui != gui) {
            GUI.gui = gui;
        }
        gui.tick();
        if (activeChange) {
            for (Text tt : gui.text) {
                if (tt.wasClicked() || handler.getMM().isRightClicked()) {
                    for (Text t : gui.text) {
                        if (t.getText().equals("Character Sheet")) {
                            t.setY((tiles.cords(50, 5)[1]));
                        } else {
                            t.setY(tiles.cords(40, yOffset)[1]);
                        }
                        yOffset += 10;
                        t.active = true;
                    }
                    yOffset = 30;
                    activeChange = false;
                }
            }
        } else
            for (Text t : gui.text) {
                if (t.wasClicked()) {
                    if (t.getText().equals("Character Sheet")) {
                        continue;
                    }
                    for (Text tt : gui.text) {
                        if (tt == t) {
                            t.setY((tiles.cords(50, 5)[1]));
                        } else {
                            tt.active = false;
                        }
                    }
                    activeChange = true;
                }
            }
    }

    @Override
    public void render(Graphics g) {
        gui.render(g);
    }
}
