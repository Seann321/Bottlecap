package bottlecap.states;

import bottlecap.Bottlecap;
import bottlecap.controls.KeyManager;
import bottlecap.controls.MouseManager;
import bottlecap.states.gamestate.GameState;
import bottlecap.states.voidstate.VoidState;

public class Handler {

    private Bottlecap bottlecap;
    private MouseManager mouseManager;
    private KeyManager keyManager;

    //States
    public GameState gameState;
    public VoidState voidState;

    public Handler(Bottlecap bottlecap){
        this.bottlecap = bottlecap;
        mouseManager = new MouseManager(this);
        keyManager = new KeyManager();
        gameState = new GameState(this);
        voidState = new VoidState(this);
        setCurrentState(voidState);
    }

    public int getHeight() {return bottlecap.getHeight();}

    public int getWidth() {return bottlecap.getWidth();}

    public MouseManager getMM(){
        return mouseManager;
    }

    public KeyManager getKM(){
        return keyManager;
    }

    public State getCurrentState(){
        return State.CurrentState;
    }

    public void setCurrentState(State x){
        State.CurrentState = x;
    }

}
