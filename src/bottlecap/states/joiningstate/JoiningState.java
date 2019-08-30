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
    private Tiles tiles;
    Text ipMessage;

    public JoiningState(Handler handler) {
        super(handler);
        tiles = new Tiles(handler);
        textStrings.add(new Text("IP Address:",(tiles.cords(48,15)[0]),(tiles.cords(48,15)[1]),Text.lFont,Color.LIGHT_GRAY));
        ipMessage = new Text("", (tiles.cords(49, 25)[0]), (tiles.cords(49, 25)[1]),Text.mFont,Color.LIGHT_GRAY);
    }

    @Override
    public void tick() {
        IPAddress += getNumberPressed();
        if(handler.getKM().keyJustPressed(KeyEvent.VK_BACK_SPACE) && IPAddress.length() > 0){
            IPAddress = IPAddress.substring(0,IPAddress.length()-1);
        }

        ipMessage.setText(IPAddress);

        if (handler.getKM().keyJustPressed(KeyEvent.VK_ENTER)) {
            handler.client.connectToServer(IPAddress);
            handler.sendMessage("1");
            handler.setCurrentState(handler.voidState);
        }
    }

    @Override
    public void render(Graphics g) {
        ipMessage.render(g);
        for (Text t : textStrings) {
            t.render(g);
        }
    }

    public String getNumberPressed() {
        if (handler.getKM().keyJustPressed(KeyEvent.VK_0) || handler.getKM().keyJustPressed(KeyEvent.VK_NUMPAD0)) {
            return "0";
        } else if (handler.getKM().keyJustPressed(KeyEvent.VK_1) || handler.getKM().keyJustPressed(KeyEvent.VK_NUMPAD1)) {
            return "1";
        } else if (handler.getKM().keyJustPressed(KeyEvent.VK_2) || handler.getKM().keyJustPressed(KeyEvent.VK_NUMPAD2)) {
            return "2";
        } else if (handler.getKM().keyJustPressed(KeyEvent.VK_3) || handler.getKM().keyJustPressed(KeyEvent.VK_NUMPAD3)) {
            return "3";
        } else if (handler.getKM().keyJustPressed(KeyEvent.VK_4) || handler.getKM().keyJustPressed(KeyEvent.VK_NUMPAD4)) {
            return "4";
        } else if (handler.getKM().keyJustPressed(KeyEvent.VK_5) || handler.getKM().keyJustPressed(KeyEvent.VK_NUMPAD5)) {
            return "5";
        } else if (handler.getKM().keyJustPressed(KeyEvent.VK_6) || handler.getKM().keyJustPressed(KeyEvent.VK_NUMPAD6)) {
            return "6";
        } else if (handler.getKM().keyJustPressed(KeyEvent.VK_7) || handler.getKM().keyJustPressed(KeyEvent.VK_NUMPAD7)) {
            return "7";
        } else if (handler.getKM().keyJustPressed(KeyEvent.VK_8) || handler.getKM().keyJustPressed(KeyEvent.VK_NUMPAD8)) {
            return "8";
        } else if (handler.getKM().keyJustPressed(KeyEvent.VK_9) || handler.getKM().keyJustPressed(KeyEvent.VK_NUMPAD9)) {
            return "9";
        } else if (handler.getKM().keyJustPressed(KeyEvent.VK_PERIOD) || handler.getKM().keyJustPressed(KeyEvent.VK_DECIMAL)) {
            return ".";
        } else {
            return "";
        }
    }
}
