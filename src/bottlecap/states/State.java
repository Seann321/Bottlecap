package bottlecap.states;


import bottlecap.assets.GUI;

import java.awt.*;

public abstract class State {

    public static State CurrentState = null;
    protected Handler handler;
    protected GUI gui;

    public State(Handler handler){
        this.handler = handler;
    }

    public abstract  void tick();

    public abstract void render(Graphics g);

    public GUI getGUI(){
        return gui;
    }
}
