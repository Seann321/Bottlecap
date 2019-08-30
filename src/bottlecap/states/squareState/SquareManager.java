package bottlecap.states.squareState;

import bottlecap.states.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SquareManager {

    private ArrayList<Square> squares = new ArrayList<>();
    private ArrayList<Square> newSquares = new ArrayList<>();
    private Handler handler;

    SquareManager(Handler handler) {
        this.handler = handler;
    }

    public void tick() {

        if (handler.getKM().keyJustPressed(KeyEvent.VK_R)) {
            newSquares.clear();
        }

        if (handler.getMM().isRightPressed()) {
            newSquares.add(new Square(new Rectangle(handler.getMM().getMouseX(),
                    handler.getMM().getMouseY(), 10, 10), handler));
        }


        squares.clear();
        squares.addAll(newSquares);
        for (Square x : squares) {
            x.tick();
        }

    }

    public void render(Graphics g) {

        for (Square x : squares) {
            x.render(g);
        }
    }

}
