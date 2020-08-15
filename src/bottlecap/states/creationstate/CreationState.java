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

    public CreationState(Handler handler) {
        super(handler);
        gui = new GUI();
        tiles = new Tiles(handler);
        gui.addText(new Text("Character Sheet", tiles.cords(50, 5), Text.lFont, true, Color.lightGray,false));
        createGrid();
    }

    public void createGrid(){
        int xWidth = handler.getWidth()/5;
        int yHeight = handler.getHeight()/12;
        for(int i = 0; i < yHeight; i++){
            for(int ii = 0; ii < xWidth; ii++){
                grids.add(new Stat(new Rectangle(i * xWidth, ii * yHeight, xWidth, yHeight)));
            }
        }
    }

    @Override
    public void tick() {
        if (GUI.gui != gui) {
            GUI.gui = gui;
        }
        if(handler.getKM().keyJustPressed(KeyEvent.VK_ESCAPE)) handler.setCurrentState(handler.voidState);
        gui.tick();
    }

    @Override
    public void render(Graphics g) {
        for(Stat s : grids){
            g.setColor(Color.red);
            Rectangle r = s.bounds;
            g.drawRect(r.x,r.y,r.width,r.height);
        }
        gui.render(g);
    }
}
