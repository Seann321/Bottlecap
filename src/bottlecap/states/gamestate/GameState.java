package bottlecap.states.gamestate;

import bottlecap.states.Handler;
import bottlecap.states.State;

import java.awt.*;

public class GameState extends State {

    private SquareManager squareManager;

    public GameState(Handler handler) {
        super(handler);
        squareManager = new SquareManager(handler);
    }

    @Override
    public void tick() {
        squareManager.tick();
    }

    @Override
    public void render(Graphics g) {
        squareManager.render(g);
    }
}
