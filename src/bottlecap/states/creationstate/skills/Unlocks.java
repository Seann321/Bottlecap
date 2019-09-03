package bottlecap.states.creationstate.skills;

public class Unlocks {

    String title;
    Enum branch;
    int position;
    String description;
    Boolean unlocked=false;
    Boolean activated=false;

    public Unlocks(String title, Enum branch, int position, String description) {
        this.title = title;
        this.branch = branch;
        this.position = position;
        this.description = description;

        if(branch==Skills.Branches.BASE) unlocked=true;

    }

    public void tick(){



    }

    public String getTitle() {
        return title;
    }
}
