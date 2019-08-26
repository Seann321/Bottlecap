package bottlecap.states.voidstate;

import bottlecap.states.Handler;
import bottlecap.states.State;

import java.awt.*;

public class VoidState extends State {

    private PlayerManager playerManager;

    public VoidState(Handler handler) {
        super(handler);
        playerManager = new PlayerManager(handler);
    }

    @Override
    public void tick() {
        playerManager.tick();
    }

    @Override
    public void render(Graphics g) {
        playerManager.render(g);
    }
}
