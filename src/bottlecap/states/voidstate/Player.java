package bottlecap.states.voidstate;

import bottlecap.states.Handler;
import org.w3c.dom.css.Rect;

import java.awt.*;

public class Player {

    private Rectangle screen;
    private Rectangle player;
    private Rectangle ghostPlayer;
    private Handler handler;
    private final int SPEED = 5;

    public Player(Rectangle player, Handler handler) {
        this.player = player;
        this.handler = handler;
        screen = new Rectangle(0, 0, handler.getWidth(), handler.getHeight());
        ghostPlayer = player;
    }

    public void tick() {

        movement();

    }

    public void render(Graphics g) {

        g.setColor(Color.gray);
        g.fillRect(player.x, player.y, player.width, player.height);

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
}

