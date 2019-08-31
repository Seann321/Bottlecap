package bottlecap.states.creationstate;

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
    Color darkOrange = new Color(255, 140, 0);

    public CreationState(Handler handler) {
        super(handler);
        tiles = new Tiles(handler);
        yOffset = 30;
        text.add(new Text("Character Sheet", (handler.getWidth() / 2), (tiles.cords(50, 5)[1]), Text.lFont, true, Color.lightGray));
        text.add(new Text("Strength", (handler.getWidth() / 2), (tiles.cords(50, 5)[1]), Text.lFont, true, Color.red));
        text.add(new Text("Magic", (handler.getWidth() / 2), (tiles.cords(50, 5)[1]), Text.lFont, true, Color.blue));
        text.add(new Text("Perception", (handler.getWidth() / 2), (tiles.cords(50, 5)[1]), Text.lFont, true, Color.MAGENTA));
        text.add(new Text("Charisma", (handler.getWidth() / 2), (tiles.cords(50, 5)[1]), Text.lFont, true, Color.green));
        text.add(new Text("Defence", (handler.getWidth() / 2), (tiles.cords(50, 5)[1]), Text.lFont, true, darkOrange));
        text.add(new Text("Agility", (handler.getWidth() / 2), (tiles.cords(50, 5)[1]), Text.lFont, true, Color.cyan));


        for (Text t : text) {

            if (t.getText().equals("Character Sheet")) continue;
            t.setY(tiles.cords(40, yOffset)[1]);
            yOffset += 10;

        }

    }

    @Override
    public void tick() {


    }

    @Override
    public void render(Graphics g) {

        for (Text t : text) {
            if(t.getBounds().contains(handler.getMM().getMouseX(),handler.getMM().getMouseY())&& !(t.getText().equals("Character Sheet"))){
                t.color = Color.yellow;
            }else{
                t.color = t.originalColor;
            }
                t.render(g);
        }
    }
}
