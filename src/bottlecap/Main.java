package bottlecap;

import java.awt.*;

public class Main {

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static void main(String[] args){

        Display display = new Display("Testing", screenSize.width, screenSize.height);

    }

}
