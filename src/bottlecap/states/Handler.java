package bottlecap.states;

import bottlecap.Bottlecap;
import bottlecap.controls.KeyManager;
import bottlecap.controls.MouseManager;
import bottlecap.states.gamestate.GameState;

public class Handler {

    private Bottlecap bottlecap;
    private MouseManager mouseManager;
    private KeyManager keyManager;

    //States
    public GameState gameState;

    public Handler(Bottlecap bottlecap){
        this.bottlecap = bottlecap;
        mouseManager = new MouseManager();
        keyManager = new KeyManager();
        gameState = new GameState(this);
        setCurrentState(gameState);
    }

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
