package bottlecap.states;

import bottlecap.controls.KeyManager;
import bottlecap.controls.MouseManager;

public class Handler {

    private MouseManager mouseManager;
    private KeyManager keyManager;

    public void Hander(){
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
