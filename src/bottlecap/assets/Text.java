package bottlecap.assets;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Text {

    public static Font sFont;
    public static Font mFont;
    public static Font lFont;
    public FontMetrics fm;
    public Boolean center;
    public Color color;
    public Color originalColor;
    public Rectangle bounds;

    String message;
    int x, y;
    Font font;

    public Text(String message, int x, int y, Font font, Boolean center, Color color) {
        this.font = font;
        this.center = center;
        this.x = x;
        this.y = y;
        this.message = message;
        this.color = color;
        originalColor = color;
        bounds = new Rectangle(0, 0, 0, 0);
    }

    public void setText(String x) {
        message = x;
    }

    public String getText() {
        return message;
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
        if (fm == null) fm = g.getFontMetrics(font);
        drawString(g, message, x, y, center, color, font);
    }

    public void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color c, Font font) {
        g.setColor(c);
        g.setFont(font);
        int x = xPos;
        int y = yPos;

        if (center) {
            x = xPos - fm.stringWidth(text) / 2;
            y = (yPos - fm.getHeight() / 2) + fm.getAscent();
        }
        bounds = new Rectangle(x, y - (fm.getHeight() - 5), fm.stringWidth(text), fm.getHeight());


        g.drawString(text, x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
