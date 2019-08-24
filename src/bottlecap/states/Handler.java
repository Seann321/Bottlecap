package bottlecap.states;

import bottlecap.Bottlecap;
import bottlecap.controls.KeyManager;
import bottlecap.controls.MouseManager;

public class Handler {

    private Bottlecap bottlecap;
    private MouseManager mouseManager;
    private KeyManager keyManager;

    public Handler(Bottlecap bottlecap){
        this.bottlecap = bottlecap;
        mouseManager = new MouseManager();
        keyManager = new KeyManager();
    }

    public MouseManager getMM(){
        return mouseManager;
    }

    public KeyManager getKM(){
        return keyManager;
    }


}
