package bottlecap.states;

import bottlecap.Bottlecap;
import bottlecap.controls.KeyManager;
import bottlecap.controls.MouseManager;
import bottlecap.multiplayer.Client;
import bottlecap.states.creationstate.CreationState;
import bottlecap.states.squareState.SquareState;
import bottlecap.states.joiningstate.JoiningState;
import bottlecap.states.voidstate.VoidState;

public class Handler {

    public int computerID = 0;
    private Bottlecap bottlecap;
    private MouseManager mouseManager;
    private KeyManager keyManager;
    public Client client;
    private String lastMessage = "";

    //States
    public SquareState squareState;
    public VoidState voidState;
    public JoiningState joiningState;
    public CreationState creationState;

    public Handler(Bottlecap bottlecap) {
        this.bottlecap = bottlecap;
        mouseManager = new MouseManager(this);
        keyManager = new KeyManager();
        client = new Client(this);
        squareState = new SquareState(this);
        voidState = new VoidState(this);
        joiningState = new JoiningState(this);
        creationState = new CreationState(this);
        setCurrentState(voidState);
    }

    public void sendMessage(String x){
        client.sendMessage(x+"ID"+computerID);
    }

    public void setLastMessage(String x){
        lastMessage = x;
    }

    public String recieveMessage(){
        if(lastMessage.contains(""+computerID)){
            lastMessage = "";
        }
        String temp = lastMessage;
        lastMessage = "";
        return temp;
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
