package bottlecap;

import java.awt.*;
import java.io.IOException;

public class Main {

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static Bottlecap bottlecap;

    public static void main(String[] args) throws IOException {

        bottlecap = new Bottlecap("Testing", screenSize.width, screenSize.height);
        bottlecap.start();

    }

}
