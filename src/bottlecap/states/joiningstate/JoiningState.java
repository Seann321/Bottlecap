package bottlecap.states.joiningstate;

import bottlecap.assets.Text;
import bottlecap.states.Handler;
import bottlecap.states.State;
import bottlecap.states.Tiles;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class JoiningState extends State {

    private ArrayList<Text> textStrings = new ArrayList<>();
    private String IPAddress = "";
    Tiles tiles;

    public JoiningState(Handler handler) {
        super(handler);
        tiles = new Tiles(handler);
        textStrings.add(new Text("IP Address:",(tiles.cords(48,15)[0]),(tiles.cords(48,15)[1]),Text.lFont));
    }

    @Override
    public void tick() {
        handler.client.connectToServer();
        handler.sendMessage("1");
        handler.setCurrentState(handler.voidState);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        for(Text t : textStrings){
            t.render(g);
        }
    }
}
