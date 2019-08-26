package bottlecap.states;

import bottlecap.Bottlecap;
import bottlecap.controls.KeyManager;
import bottlecap.controls.MouseManager;
import bottlecap.multiplayer.Client;
import bottlecap.states.gamestate.GameState;
import bottlecap.states.joiningstate.JoiningState;
import bottlecap.states.voidstate.VoidState;

public class Handler {

    private Bottlecap bottlecap;
    private MouseManager mouseManager;
    private KeyManager keyManager;
    public Client client;

    //States
    public GameState gameState;
    public VoidState voidState;
    public JoiningState joiningState;

    public Handler(Bottlecap bottlecap) {
        this.bottlecap = bottlecap;
        mouseManager = new MouseManager(this);
        keyManager = new KeyManager();
        gameState = new GameState(this);
        voidState = new VoidState(this);
        client = new Client(this);
        joiningState = new JoiningState(this);
        setCurrentState(voidState);
    }

    public void sendMessage(String x){
        client.sendMessage(x);
    }

    public int getHeight() {
        return bottlecap.getHeight();
    }

    public int getWidth() {
        return bottlecap.getWidth();
    }

    public MouseManager getMM() {
        return mouseManager;
    }

    public KeyManager getKM() {
        return keyManager;
    }

    public State getCurrentState() {
        return State.CurrentState;
    }

    public void setCurrentState(State x) {
        State.CurrentState = x;
    }

}
