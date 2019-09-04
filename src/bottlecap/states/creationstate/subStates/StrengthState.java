package bottlecap.states.creationstate.subStates;

import bottlecap.assets.GUI;
import bottlecap.assets.Text;
import bottlecap.states.Handler;
import bottlecap.states.State;
import bottlecap.states.Tiles;
import bottlecap.states.creationstate.skills.Skills;
import bottlecap.states.creationstate.skills.Strength;

import java.awt.*;

public class StrengthState extends State {
    Strength strength = new Strength();
    Tiles tiles;


    public StrengthState(Handler handler, Tiles tiles) {
        super(handler);
        gui = new GUI();
        this.tiles=tiles;
        gui.addText(new Text("Strength", tiles.cords(50, 5), Text.lFont, true, Color.red));
        gui.addText(new Text(strength.getUnlock(Skills.Branches.BASE, 0).getTitle(), tiles.cords(50, 50), Text.mFont, true, Color.LIGHT_GRAY));

    }

    //TODO Hey Look at these neat TODO things. At the bottom the the screen you can look at all of them through the whole program.

    @Override
    public void tick() {
        gui.tick();
        back();
    }

    @Override
    public void render(Graphics g) {
        gui.render(g);
    }

    public void back(){
        for(Text t :gui.text){
            if(t.getText().equals("Strength") && t.wasClicked()) handler.setCurrentState(handler.creationState);
        }
        if(handler.getMM().isRightClicked()) handler.setCurrentState(handler.creationState);
    }
}
