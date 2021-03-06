package bottlecap.assets;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class Text {

    public static Font sFont;
    public static Font mFont;
    public static Font lFont;
    public FontMetrics fm;
    public Boolean center = false, right = false;
    public Color color;
    public Color originalColor;
    public Rectangle bounds;
    protected boolean clicked = false, rightClicked = false, hovering = false;
    public boolean active = true, clickable = true;

    public String message;
    int x, y;
    Font font;

    public Text(String message, int cords[], Font font, Boolean center, Color color) {
        this.font = font;
        this.center = center;
        x = cords[0];
        y = cords[1];
        this.message = message;
        this.color = color;
        originalColor = color;
        bounds = new Rectangle(0, 0, 0, 0);
    }

    public Text(String message, int cords[], Font font, Boolean center, Color color, boolean clickable) {
        this.font = font;
        this.center = center;
        x = cords[0];
        y = cords[1];
        this.message = message;
        this.color = color;
        originalColor = color;
        bounds = new Rectangle(0, 0, 0, 0);
        this.clickable = clickable;
    }

    public Text(String message, int cords[], Font font, Color color, boolean clickable, Boolean right) {
        this.font = font;
        this.right = right;
        x = cords[0];
        y = cords[1];
        this.message = message;
        this.color = color;
        originalColor = color;
        bounds = new Rectangle(0, 0, 0, 0);
        this.clickable = clickable;
    }


    public void tick() {
        if (clickable) {
            if (hovering) {
                color = Color.yellow;
            } else {
                color = originalColor;
            }
        }
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
        drawString(g, message, x, y, color, font);
    }

    public void drawString(Graphics g, String text, int xPos, int yPos, Color c, Font font) {
        g.setColor(c);
        g.setFont(font);
        int x = xPos;
        int y = yPos;

        if (center) {
            x = xPos - fm.stringWidth(text) / 2;
            y = (yPos - fm.getHeight() / 2) + fm.getAscent();
        }
        if (right) {
            x = xPos - fm.stringWidth(text);
            y = (yPos - fm.getHeight()) + fm.getAscent();
        }
        bounds = new Rectangle(x, y - (fm.getHeight() - 5), fm.stringWidth(text), fm.getHeight());


        g.drawString(text, x, y);
    }

    public void onClick() {
        if (!active) {
            return;
        }
        clicked = true;
        hovering = false;
    }

    public void onRightClick() {
        if (!active) {
            return;
        }
        rightClicked = true;
        hovering = false;
    }

    public void onMouseMove(MouseEvent e) {
        if (bounds.contains(e.getX(), e.getY())) {
            hovering = true;
        } else {
            hovering = false;
        }
    }

    public void onMouseReleased(MouseEvent e) {
        if (hovering) {
            if (e.getButton() == 1) {
                onClick();
            }
            if (e.getButton() == 3) {
                onRightClick();
            }
        }
    }

    public boolean wasClicked() {
        if (clicked) {
            clicked = false;
            return true;
        }
        return false;
    }

    public boolean wasRightClicked() {
        if (rightClicked) {
            rightClicked = false;
            return true;
        }
        return false;
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

    public void setText(String x) {
        message = x;
    }

    public String getText() {
        return message;
    }

}
