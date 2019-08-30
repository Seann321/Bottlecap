package bottlecap.states.squareState;

import bottlecap.states.Handler;
import bottlecap.states.State;

import java.awt.*;
import java.awt.event.KeyEvent;

public class SquareState extends State {

    private SquareManager squareManager;

    public SquareState(Handler handler) {
        super(handler);
        squareManager = new SquareManager(handler);
    }

    @Override
    public void tick() {
        if(handler.getKM().keyJustPressed(KeyEvent.VK_ENTER)){
            handler.setCurrentState(handler.voidState);
        }
        squareManager.tick();
    }

    @Override
    public void render(Graphics g) {
        squareManager.render(g);
    }
}
