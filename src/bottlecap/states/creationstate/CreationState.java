package bottlecap.states.creationstate;

import bottlecap.assets.GUI;
import bottlecap.assets.Text;
import bottlecap.states.Handler;
import bottlecap.states.State;
import bottlecap.states.Tiles;
import bottlecap.states.creationstate.subStates.StrengthState;

import java.awt.*;

public class CreationState extends State {

    private StrengthState strengthState;
    private Tiles tiles;
    private int yOffset;
    Color darkOrange = new Color(255, 140, 0);


    public CreationState(Handler handler) {
        super(handler);
        gui = new GUI();
        tiles = new Tiles(handler);
        yOffset = 30;
        strengthState = new StrengthState(handler, tiles);

        gui.addText(new Text("Character Sheet", tiles.cords(50, 5), Text.lFont, true, Color.lightGray,false));
        gui.addText(new Text("Strength", tiles.cords(50, 5), Text.lFont, true, Color.red));
        gui.addText(new Text("Magic", tiles.cords(50, 5), Text.lFont, true, Color.blue));
        gui.addText(new Text("Perception", tiles.cords(50, 5), Text.lFont, true, Color.MAGENTA));
        gui.addText(new Text("Charisma", tiles.cords(50, 5), Text.lFont, true, Color.green));
        gui.addText(new Text("Defence", tiles.cords(50, 5), Text.lFont, true, darkOrange));
        gui.addText(new Text("Agility", tiles.cords(50, 5), Text.lFont, true, Color.cyan));

        for (Text t : gui.newText) {
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

        for (Text t : gui.text) {
            if (t.wasClicked()) {
                if (t.getText().equals("Character Sheet")) {
                    continue;
                }
                if (t.getText().equals("Strength")) {
                    handler.setCurrentState(strengthState);
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        gui.render(g);
    }
}
