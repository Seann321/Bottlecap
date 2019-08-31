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
    Color darkOrange = new Color(255, 140, 0);

    public CreationState(Handler handler) {
        super(handler);
        gui = new GUI();
        GUI.gui = gui;
        tiles = new Tiles(handler);
        yOffset = 30;
        gui.addText(new Text("Character Sheet", (handler.getWidth() / 2), (tiles.cords(50, 5)[1]), Text.lFont, true, Color.lightGray));
        gui.addText(new Text("Strength", (handler.getWidth() / 2), (tiles.cords(50, 5)[1]), Text.lFont, true, Color.red));
        gui.addText(new Text("Magic", (handler.getWidth() / 2), (tiles.cords(50, 5)[1]), Text.lFont, true, Color.blue));
        gui.addText(new Text("Perception", (handler.getWidth() / 2), (tiles.cords(50, 5)[1]), Text.lFont, true, Color.MAGENTA));
        gui.addText(new Text("Charisma", (handler.getWidth() / 2), (tiles.cords(50, 5)[1]), Text.lFont, true, Color.green));
        gui.addText(new Text("Defence", (handler.getWidth() / 2), (tiles.cords(50, 5)[1]), Text.lFont, true, darkOrange));
        gui.addText(new Text("Agility", (handler.getWidth() / 2), (tiles.cords(50, 5)[1]), Text.lFont, true, Color.cyan));

        for (Text t : gui.text) {
            if (t.getText().equals("Character Sheet")) continue;
            t.setY(tiles.cords(40, yOffset)[1]);
            yOffset += 10;
        }
    }

    @Override
    public void tick() {
        gui.tick();
        for(Text t : gui.text){
            if(t.wasClicked()){
                System.out.println(true);
            }
        }

    }

    @Override
    public void render(Graphics g) {
        gui.render(g);
    }
}
