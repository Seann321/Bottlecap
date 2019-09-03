package bottlecap.states.creationstate.skills;

public class Strength extends Skills {

    public enum Branches{
        UNARMED,ONEHANDED,TWOHANDED
    }

    public Strength(){
        unlocks.add(new Unlocks("Power+",Skills.Branches.BASE,0,"Increase all melee attacks by 5%"));
        unlocks.add(new Unlocks("Unarmed I",Branches.UNARMED,0,"Unarmed attacks deal more damage"));
        unlocks.add(new Unlocks("Unarmed II",Branches.UNARMED,1,"Unarmed attacks deal more damage"));
        unlocks.add(new Unlocks("One Handed I",Branches.ONEHANDED,0,"Attacks with one handed weapons do more damage"));
        unlocks.add(new Unlocks("One Handed II",Branches.ONEHANDED,1,"Attacks with one handed weapons do more damage"));
        unlocks.add(new Unlocks("Two Handed I",Branches.TWOHANDED,0,"Attacks with two handed weapons do more damage"));
        unlocks.add(new Unlocks("Two Handed II",Branches.TWOHANDED,1,"Attacks with two handed weapons do more damage"));
    }

}
