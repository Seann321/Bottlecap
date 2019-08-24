package bottlecap;

import java.awt.*;

public class Square {

    int height, width, xposition, yposition;

    public Square(int height, int width, int xposition, int yposition) {
        this.height = height;
        this.width = width;
        this.xposition = xposition;
        this.yposition = yposition;
    }

    public void tick() {

    }

    public void render(Graphics g) {

    }

    public void movement() {
        if (W) xposition--;
        if (A) yposition--;
        if (S) xposition++;
        if (D) yposition++;
    }
}
