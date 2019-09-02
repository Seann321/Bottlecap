package bottlecap.states.gameState;

import bottlecap.assets.GUI;
import bottlecap.states.Handler;
import bottlecap.states.State;
import bottlecap.states.Tiles;

import java.awt.*;

public class GameState extends State {

    private boolean debug = true;
    Tiles tiles;
    private GUI gui;

    public GameState(Handler handler) {
        super(handler);
        gui = new GUI();
        tiles = new Tiles(handler);
    }

    @Override
    public void tick() {
        gui.tick();
        debug();
    }

    public void debug(){
        if(debug){
            if (handler.getMM().isRightPressed()) {
                System.out.println("X " + tiles.tilePOS(handler.getMM().getMouseX(), handler.getMM().getMouseY())[0] + " Y " + tiles.tilePOS(handler.getMM().getMouseX(), handler.getMM().getMouseY())[1]);
            }
        }
    }

    @Override
    public void render(Graphics g) {
        gui.render(g);
        tiles.render(g);
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0,tiles.cords(0,100)[1],handler.getWidth(),handler.getHeight());
    }
}
