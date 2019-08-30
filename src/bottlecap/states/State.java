package bottlecap.states;


import java.awt.*;

public abstract class State {

    static State CurrentState = null;
    protected Handler handler;

    public State(Handler handler){
        this.handler = handler;
    }

    public abstract  void tick();

    public abstract void render(Graphics g);

}
