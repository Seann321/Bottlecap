package bottlecap.states.creationstate;

import bottlecap.assets.GUI;
import bottlecap.assets.Text;
import bottlecap.states.Handler;
import bottlecap.states.State;
import bottlecap.states.Tiles;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class CreationState extends State {

    private Tiles tiles;
    public ArrayList<Stat> grids = new ArrayList<>();
    public Stat selectedGrid;
    public int strength, defense;
    public Text confirmBox;

    public CreationState(Handler handler) {
        super(handler);
        gui = new GUI();
        tiles = new Tiles(handler);
        gui.addText(new Text("Character Sheet", tiles.cords(50, 5), Text.lFont, true, Color.lightGray, false));
        confirmBox = new Text("Confirm", tiles.cords(50, 8), Text.mFont, true, Color.lightGray, true);
        gui.addText(confirmBox);
        gui.addText(new Text("Unused Points", tiles.cords(50, 5), Text.lFont, true, Color.lightGray, false));
        gui.addText(new Text("Strength", tiles.cords(50, 5), Text.lFont, true, Color.lightGray, false));
        gui.addText(new Text("Defense", tiles.cords(50, 5), Text.lFont, true, Color.lightGray, false));
        createGrid();
    }

    int xWidth = handler.getWidth() / 5;
    int yHeight = handler.getHeight() / 12;

    public void createGrid() {
        for (int i = 0; i < yHeight; i++) {
            for (int ii = 0; ii < xWidth; ii++) {
                grids.add(new Stat(new Rectangle(i * xWidth, ii * yHeight, xWidth, yHeight)));
            }
        }
        for (Stat s : grids) {
            if (s.bounds.y <= yHeight || s.bounds.x == xWidth * 2) {
                s.color = Color.red;
            }
            if (s.bounds.y <= yHeight || s.bounds.x == xWidth) {
                s.color = Color.blue;
            }
            if (s.bounds.y <= yHeight || s.bounds.x >= xWidth * 3) {
                s.color = Color.black;
                s.locked = true;
            }
            if (s.color == Color.gray && s.bounds.y >= yHeight * 4) {
                s.filled = true;
            }
            if (s.color == Color.blue && s.bounds.y >= yHeight * 11) {
                s.filled = true;
            }
            if (s.color == Color.red && s.bounds.y >= yHeight * 11) {
                s.filled = true;
            }
        }
    }

    @Override
    public void tick() {
        if (GUI.gui != gui) {
            GUI.gui = gui;
        }
        if(confirmBox.wasClicked()){
            handler.setCurrentState(handler.gameState);
        }
        if (handler.getKM().keyJustPressed(KeyEvent.VK_ESCAPE)) handler.setCurrentState(handler.voidState);
        gui.tick();
        selectGrid();
        centerText("Unused Points", Color.gray);
        centerText("Strength", Color.red);
        centerText("Defense", Color.blue);
        int i = 0;
        int ii = 0;
        for (Stat s : grids) {
            if (s.filled) continue;
            if (s.color == Color.red) {
                i++;
            }
            if (s.color == Color.blue) {
                ii++;
            }
        }
        strength = 10 - i;
        defense = 10 - ii;
    }

    private void centerText(String text, Color color) {
        for (Text t : gui.text) {
            if (t.message.equals(text)) {
                for (Stat s : grids) {
                    if (s.color == color && s.filled) {
                        t.setX(s.bounds.x + xWidth / 2);
                        t.setY(s.bounds.y + yHeight / 2);
                        return;
                    }
                }
            }
        }
    }

    private void selectGrid() {
        if ((handler.getMM().isLeftPressed()) && selectedGrid == null) {
            for (Stat s : grids) {
                if (s.locked || !s.filled) continue;
                if (s.bounds.contains(handler.getMM().getMouseX(), handler.getMM().getMouseY())) {
                    selectedGrid = s;
                }
            }
        }
        if (selectedGrid == null) return;
        selectedGrid.bounds.x = (handler.getMM().getMouseX() - (selectedGrid.bounds.width / 2));
        selectedGrid.bounds.y = (handler.getMM().getMouseY() - (selectedGrid.bounds.height / 2));
        if (!handler.getMM().isLeftPressed() && selectedGrid != null) {
            for (Stat s : grids) {
                if (s.equals(selectedGrid))
                    continue;
                if (s.bounds.contains(handler.getMM().getMouseX(), handler.getMM().getMouseY()) && !s.locked && !s.filled) {
                    s.filled = true;
                    selectedGrid.filled = false;
                }
            }
            selectedGrid.bounds.x = selectedGrid.startCords[0];
            selectedGrid.bounds.y = selectedGrid.startCords[1];
            selectedGrid = null;
        }
    }

    @Override
    public void render(Graphics g) {
        for (Stat s : grids) {
            if (s.filled) {
                g.setColor(s.color);
                Rectangle r = s.bounds;
                g.fillRect(r.x, r.y, r.width, r.height);
                g.setColor(Color.white);
                g.drawRect(r.x, r.y, r.width, r.height);
            }
        }

        gui.render(g);
    }
}
