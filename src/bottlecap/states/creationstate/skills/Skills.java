package bottlecap.states.creationstate.skills;

import java.util.ArrayList;

public abstract class Skills {

    public int level = 1,xp = 0;
    public int skillPoints = 3;
    public int skillPointsFromLevel = 4;
    private int[] xpLevelReq = new int[]{0,300,900,2700,6500,14000,23000,34000,48000,64000,85000,100000,120000,140000,165000,195000,225000,265000,305000,355000};
    public ArrayList<Unlocks> unlocks = new ArrayList<>();
    public enum Branches{
        BASE
    }


    public void checkForLevelUp(){
        if(xpLevelReq[level] <= xp){
            levelUp();
        }
    }

    public void levelUp(){
        level++;
        skillPoints += skillPointsFromLevel;
    }

    public Unlocks getUnlock(Enum Branch,int position){

        for(Unlocks u : unlocks){
            if(u.branch==Branch){
                if(u.position==position) return u;
            }
        }
        return null;
    }


}
