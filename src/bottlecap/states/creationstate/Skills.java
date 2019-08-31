package bottlecap.states.creationstate;

public abstract class Skills {

    public int level = 1,xp = 0;
    public int skillPoints = 3;
    public int skillPointsFromLevel = 4;

    /*
    1 - 0
    2 - 300
    3 - 900
    4 - 2700
    5 - 6500
    6 - 14000
    7 - 23000
    8 - 34000
    9 - 48000
    10 - 64000
    11 - 85000
    12 - 100000
    13 - 120000
    14 - 140000
    15 - 165000
    16 - 195000
    17 - 225000
    18 - 265000
    19 - 305000
    20 - 355000
     */

    public void checkForLevelUp(){
        if(level == 1 && xp >= 300){
            levelUp();
        }else if(level == 2 && xp >= 900){
            levelUp();
        }else if(level == 3 && xp >= 2700){
            levelUp();
        }else if(level == 4 && xp >= 6500){
            levelUp();
        }else if(level == 5 && xp >= 14000){
            levelUp();
        }
    }

    public void levelUp(){
        level++;
        skillPoints += skillPointsFromLevel;
    }

}
