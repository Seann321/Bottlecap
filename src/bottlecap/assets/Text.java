package bottlecap.assets;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Text {

    public static Font sFont;
    public static Font mFont;
    public static Font lFont;

    String message;
    int x, y;
    Font font;

    public Text(String message, int x, int y, Font font) {
        this.font = font;
        this.x = x;
        this.y = y;
        this.message = message;
    }


    public static void Init() {
        try {
            sFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/bottlecap/assets/slkscr.ttf")).deriveFont(16f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(sFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        try {
            mFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/bottlecap/assets/slkscr.ttf")).deriveFont(24f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(mFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        try {
            lFont = Font.createFont(Font.TRUETYPE_FONT, new File("src/bottlecap/assets/slkscr.ttf")).deriveFont(36f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(lFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g) {
        g.setFont(font);
        g.drawString(message,x,y);
    }
}
