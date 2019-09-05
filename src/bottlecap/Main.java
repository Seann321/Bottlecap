package bottlecap;

import java.awt.*;

public class Main {

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static Bottlecap bottlecap;

    public static void main(String[] args) {
        bottlecap = new Bottlecap("Testing", screenSize.width, screenSize.height);
        bottlecap.start();
    }
}