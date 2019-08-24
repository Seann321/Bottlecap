package bottlecap;

import java.awt.*;

public class Square {

    private Rectangle player;

    public Square(Rectangle player) {
        this.player=player;
    }

    public void tick() {

    }

    public void render(Graphics g) {

        g.setColor(Color.cyan);
        g.drawRect(player.x,player.y,player.width,player.height);

    }

}
