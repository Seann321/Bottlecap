package bottlecap.states.creationstate;

import bottlecap.states.Handler;
import bottlecap.states.State;

import java.awt.*;

public class CreationState extends State {

    String statString;
    int yOffset;

    public CreationState(Handler handler) {
        super(handler);
    }

    @Override
    public void tick() {



    }

    @Override
    public void render(Graphics g) {

        g.setColor(Color.green);
        g.drawString("Character Creation",handler.getWidth()/2,15);
        for (int i=0; i<7;i++){

            if(i==0) {statString="Strength"; yOffset=-90;}
            else if(i==1) {statString="Agility"; yOffset=-60;}
            else if(i==2) {statString="Perception"; yOffset=-30;}
            else if(i==3) {statString="Charisma"; yOffset=0;}
            else if(i==4) {statString="Luck"; yOffset=30;}
            else if(i==5) {statString="Endurance"; yOffset=60;}
            else if(i==6) {statString="Intelligence"; yOffset=90;}
            g.drawString(statString,handler.getWidth()/2,(handler.getHeight()/2)+yOffset);

        }



    }
}
