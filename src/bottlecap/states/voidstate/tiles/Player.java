package bottlecap.states.voidstate.tiles;

import bottlecap.states.Handler;
import org.w3c.dom.css.Rect;

import java.awt.*;

public class Player extends TileEntities {

    private Rectangle screen;
    private Rectangle player;
    private Rectangle ghostPlayer;
    private final int SPEED = 5;
    private Color color = Color.gray;
    public boolean lockMovement = false;

    public Player(Rectangle player, Handler handler) {
        super(handler);
        this.player = player;
        screen = new Rectangle(0, 0, handler.getWidth(), handler.getHeight());
        ghostPlayer = player;
        cords[0] = player.x;
        cords[1] = player.y;
    }

    public void tick() {

        if (!lockMovement)
            movement();
        cords[0] = player.x;
        cords[1] = player.y;
    }

    public void render(Graphics g) {

        g.setColor(color);
        g.fillRect(player.x, player.y, player.width, player.height);

    }

    public void setPlayerPOS(int x, int y) {

        ghostPlayer.x = x;
        ghostPlayer.y = y;
        player.x = x;
        player.y = y;
        cords[0] = x;
        cords[1] = y;

    }

    public void movement() {

        if (handler.getKM().left) {
            ghostPlayer.x -= SPEED;
            if (!screen.contains(ghostPlayer)) {
                player.x = 0;
                ghostPlayer.x = player.x;
            } else {
                player.x = ghostPlayer.x;
            }
        }
        if (handler.getKM().up) {
            ghostPlayer.y -= SPEED;
            if (!screen.contains(ghostPlayer)) {
                player.y = 0;
                ghostPlayer.y = player.y;
            } else {
                player.y = ghostPlayer.y;
            }
        }
        if (handler.getKM().right) {
            ghostPlayer.x += SPEED;
            if (!screen.contains(ghostPlayer)) {
                player.x = handler.getWidth() - player.width;
                ghostPlayer.x = player.x;
            } else {
                player.x = ghostPlayer.x;
            }
        }
        if (handler.getKM().down) {
            ghostPlayer.y += SPEED;
            if (!screen.contains(ghostPlayer)) {
                player.y = handler.getHeight() - player.height;
                ghostPlayer.y = player.y;
            } else {
                player.y = ghostPlayer.y;
            }
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Rectangle getBounds() {
        return player;
    }
}

