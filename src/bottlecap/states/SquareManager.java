package bottlecap.states;

import bottlecap.Square;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class SquareManager {

    private ArrayList<Square> squares = new ArrayList<>();
    private ArrayList<Square> newSquares = new ArrayList<>();
    private Handler handler;


    public SquareManager(Handler handler){
        this.handler=handler;
    }

    public void tick(){

        if(handler.getMM().isRightPressed()) {
            newSquares.add(new Square(new Rectangle(handler.getMM().getMouseX(),
                    handler.getMM().getMouseY(),10,10), handler));

        }


        squares.clear();
        squares.addAll(newSquares);
        Iterator<Square> it = squares.iterator();
        while(it.hasNext()){
            Square x= it.next();
            x.tick();
        }

    }

    public void render(Graphics g){

        Iterator<Square> it = squares.iterator();
        while(it.hasNext()){
            Square x= it.next();
            x.render(g);
        }
    }

}
