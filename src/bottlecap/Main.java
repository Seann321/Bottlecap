package bottlecap;

import java.awt.*;

public class Main {

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static void main(String[] args) {
        Bottlecap bottlecap = new Bottlecap("Testing", screenSize.width, screenSize.height);
        bottlecap.start();
    }

}
