
import java.util.ArrayList;


public class Summons {



    //Summons class will serve to initialize objects that will be used as the statistics of summons

    //Name and type
    private final String name;

    private final DamageCalc.Type type1;
    private final DamageCalc.Type type2;

    public DamageCalc.Type getType1() {
        return type1;
    }
    public DamageCalc.Type getType2() {
        return type2;
    }

    //Stats for each summon
    private boolean fainted = false;
    private final int atk;
    private final int spatk;
    private final int def;
    private final int spdef;
    private int hp;
    private final int spd;
    private ArrayList<Moves> moves;


    //damage multiplier based on type
    private final int maxhp;
    private Moves.Status status = Moves.Status.Null;
    private ArrayList<Moves.Status> statuses;
   //status effect timers
    private int timedAtk = 0;
    private int timedDef = 0;
    private int timedSpAtk = 0;
    private int timedSpDef = 0;
    private int timedSpd = 0;

    private int timedPois = 0;
    private int timedPar = 0;
    private int timedBurn = 0;
    private int timedSlp = 0;

    /*
    getStatTimer - getter for the variable that tracks a statbuff
    @status - the statbuff to be found
    @return - the timer on the statbuff
     */
    public int getStatTimer(Moves.Status status) {
        int stat = -1;
        switch (status) {
            case Atk -> {
                stat = timedAtk;
            }
            case Def -> {
                stat = timedDef;

            }
            case SpAtk -> {
                stat = timedSpAtk;
            }
            case SpDef -> {
                stat = timedSpDef;
            }
            case Spd -> {
                stat = timedSpd;
            }
            case Pois -> {
                stat = timedPois;
            }
            case Par -> {
                stat = timedPar;
            }
            case Burn -> {
                stat = timedBurn;
            }
            case Slp -> {
                stat = timedSlp;
            }
        }
        if (stat == -1) {
            System.out.println("what the hell");
        }
        return stat;
    }
    /*
    getStatTimeTick - ticks one of the statTimer variables
    @status - the status that will specify the stattimer to be ticked
    @return - void
     */
    public void statTimerTick(Moves.Status status){
        switch (status) {
            case Atk -> {
                timedAtk++;
            }
            case Def -> {
               timedDef++;

            }
            case SpAtk -> {
                timedSpAtk++;
            }
            case SpDef -> {
                timedSpDef++;
            }
            case Spd -> {
                timedSpd++;
            }
            case Pois -> {
             timedPois++;
            }
            case Par -> {
                timedPar++;
            }
            case Burn -> {
                timedBurn++;
            }
            case Slp -> {
              timedSlp++;
            }
        }

    }
    /*
    statTimerZero - sets statTimer to zero
    @status - the status of the stattimer that gets reset
    @return - void
     */
    public void statTimerZero(Moves.Status status){
        switch (status) {
            case Atk -> {
                timedAtk = 0;
            }
            case Def -> {
                timedDef = 0;

            }
            case SpAtk -> {
                timedSpAtk = 0;
            }
            case SpDef -> {
                timedSpDef = 0;
            }
            case Spd -> {
                timedSpd = 0;
            }
            case Pois -> {
               timedPois = 0;
            }
            case Par -> {
                timedPar = 0;
            }
            case Burn -> {
                timedBurn = 0;
            }
            case Slp -> {
                timedSlp = 0;
            }
        }
    }


    /*
    Summons - constructor
    @name - name of the summon
    @type1 - the first type of the summon
    @type2 - the second type of the summon
    @atk - the atk value
    @spatk - the spatk value
    @def- the def value
    @spdef- the spdef value
    @maxhp - the maxhp value
    @spd - the spd value
    @return -
     */
    public Summons(String name, DamageCalc.Type type1, DamageCalc.Type type2,
                   int atk, int spatk, int def, int spdef, int maxhp, int spd){


       this.name = name;
       this.type1 = type1;
       this.type2 = type2;
       this.atk = atk;
       this.spatk = spatk;
       this.def = def;
       this.spdef = spdef;
       this.maxhp = maxhp;
       this.hp = maxhp;
       this.spd = spd;
       this.moves = new ArrayList<>();
       this.statuses = new ArrayList<>();
    }

    /*
    getMoves - getter for the arraylist of moves of the summon
    @param - none
    @return - the list of moves
     */
    public ArrayList<Moves> getMoves() {
    return moves;
    }

    /*
    getStatuses - getter for the arraylist of statuses
    @param - none
    @return - the list of statuses
     */
    public ArrayList<Moves.Status> getStatuses() {
        return statuses;
    }

    //ORGANIZATION!!!!!!
    //getter functions
    /*
    getName - the getter for the name of the summon
    @param - none
    @return - the name var
     */
    public String getName(){
        return name;
    }

    /*
    getAtk - the getter for the Atk var
    @param - none
    @return - the atk var
     */
    public int getAtk(){
        return atk;
    }
    /*
    getSpatk - the getter for the Spatk var
    @param - none
    @return - the Spatk var
     */
    public int getSpatk(){
        return spatk;
    }

    /*
    getDef - the getter for the def var
    @param - none
    @return -  the def var
     */
    public int getDef(){
        return def;
    }


    /*
    getSpdef - the getter for the SpDef var
    @param - none
    @return -  the SpDef var
     */
    public int getSpdef(){
        return spdef;
    }
    /*
    getSpd - the getter for the Spd var
    @param - none
    @return -  the Spd var
     */
    public int getSpd(){
        return spd;
    }
    /*
    getHp - the getter for the hp var
    @param - none
    @return -  the hp var
     */
    public int getHp(){
        return hp;
    }

    /*
    getMaxHp- the getter for the MaxHp var
    @param - none
    @return - MaxHp var
     */
    public  int getMaxHp(){
        return maxhp;
    }
    /*
    removeStatus - removes a specific status from the summon
    @status - the status to be removed
    @return - void
     */
    public void removeStatus(Moves.Status status){
        for (int i = 0; i < statuses.size(); i++){
            if (statuses.get(i) == status){
                statuses.remove(i);
                return;
            }
        }
    }

    /*
    getBuff - the getter for the statbuffs on the summon
    @status - the statbuffs to be found
    @return - the multiplier on the statbuff
     */
    public double getBuff( Moves.Status status){
        int x = 0;
        double modifier;
        switch (status){
            case Atk ->x =hasStatus(Moves.Status.Atk)-hasStatus(Moves.Status.LessAtk);
            case SpAtk -> x = hasStatus(Moves.Status.SpAtk)-hasStatus(Moves.Status.LessSpAtk);
            case Def -> x = hasStatus(Moves.Status.Def)-hasStatus(Moves.Status.LessDef);
            case SpDef -> x = hasStatus(Moves.Status.SpDef)-hasStatus(Moves.Status.LessSpDef);
            case Spd -> x = hasStatus(Moves.Status.Spd)-hasStatus(Moves.Status.LessSpd);

        }
        if (x>=0){
            modifier = (double) (x + 2) /2;
        }else{
            x=x*(-1);
            modifier = (double) 2 /(2+x);
        }
        return modifier;
    }

    /*
    BuffsToString - turns the list of buffs into a readable string
    @param - none
    @return - a string of buffs
     */
    public String BuffsToString(){
        String output = "";
        if (getBuff(Moves.Status.Atk)>1.0){
            output += "Atk: " + getBuff(Moves.Status.Atk)+" ";
        }
        if (getBuff(Moves.Status.SpAtk)>1.0){
            output += "SpAtk: " + getBuff(Moves.Status.SpAtk)+" ";
        }
        if (getBuff(Moves.Status.Def)>1.0){
            output += "Def: " + getBuff(Moves.Status.Def)+" ";
        }
        if (getBuff(Moves.Status.SpDef)>1.0){
            output += "SpDef: " + getBuff(Moves.Status.SpDef)+" ";
        }
        if (getBuff(Moves.Status.Spd)>1.0){
            output += "Spd: " + getBuff(Moves.Status.Spd)+" ";
        }
        return output;
    }

    /*
    hasStatus - checks if the summon has a particular status
    @status - the status to be checked
    @return - how many iterations of the status the summon has
     */
    public int hasStatus(Moves.Status status){
        int x = 0;
        for(int i = 0; i < statuses.size(); i++){
            if(statuses.get(i) == status){
                x++;
            }
        }
        return x;
    }
    /*
    isFainted - checks if the summon is fainted
    @param - none
    @return - fainted or not
     */
    public boolean isFainted(){
        return fainted;
    }

    /*
    isParalyzed - checks if the summon is paralyzed
    @param - none
    @return - para or not
     */
    public double isParalyzed(){
        double paralyzed = 1.0;
        if (status == Moves.Status.Par) {
            paralyzed = 0.5;
        }
        return paralyzed;
    }


    //setter functions!!!!
   /*
    setHp - sets the summons hp
    @param - the hp to be set
    @return - void
     */
    public void setHp(int hp){
        this.hp = hp;
    }
    /*
    setFainted - sets the summon fainted
    @fainted - if the summmon is fainted or not
    @return - void
     */
    public void setFainted(boolean fainted){
        this.fainted = fainted;
    }

    /*
    reset - resets the summon and its moves
    @param - none
    @return - void
     */
    public void reset(){
        this.hp = maxhp;
        this.fainted = false;
        for (int i = 0; i < getMoves().size(); i++){
            getMoves().get(i).reset();
        }
    }
    // genuine utility
    /*
    MoveToString - converts the moves list to a readable string
    @param -none
    @return - string of moves
     */
    public String MovesToString() {
        String output = "";

        for (int j = 0; j < this.moves.size(); j++) {
            output += "\t" + (j + 1) + ". " + this.moves.get(j).toString() + "\t\t\t\t\t\t";
            if (j % 2 == 1) {
                output += "\n\t" + this.moves.get(j - 1).getDescription() + "\t\t\t\t\t\t" + this.moves.get(j).getDescription() + "\n";
            }

        }
        return output;
    }
    /*
    addMove - adds moves
    @move - the move to be added
    @return - void
     */
    public void addMove(Moves move){
        moves.add(move);
    }

    /*
    addStatuses - adds statuses to the summon
    @status - status to be added
    @return - void
     */
    public void addStatuses(Moves.Status status){
        switch (status){
            case Null-> {
                if (!(hasStatus(Moves.Status.Null)>0)){
                    statuses.add(Moves.Status.Null);
                }
            }
            case Atk -> {
                if (!(hasStatus(Moves.Status.Atk)>5)){
                    statuses.add(Moves.Status.Atk);
                    this.timedAtk = 0;
                    System.out.printf("%s's Attack rose!\n", name);
                }else {
                    System.out.printf("%s's Attack can't go any higher!\n", name);
                }
            }
            case Def -> {
                if (!(hasStatus(Moves.Status.Def)>5)){
                    statuses.add(Moves.Status.Def);
                    this.timedDef = 0;
                    System.out.printf("%s's Defense rose!\n", name);
                } else {
                    System.out.printf("%s's Defense can't go any higher!", name);
                }
            }
            case Spd -> {
                if (!(hasStatus(Moves.Status.Spd)>5)){
                    statuses.add(Moves.Status.Spd);
                    this.timedSpd = 0;
                    System.out.printf("%s's Speed rose!\n", name);
                } else {
                    System.out.printf("%s's Speed can't go any higher!\n", name);
                }
            }
            case SpAtk -> {
                if (!(hasStatus(Moves.Status.SpAtk)>5)){
                    statuses.add(Moves.Status.SpAtk);
                    this.timedSpAtk = 0;
                    System.out.printf("%s's Special Attack rose! \n", name);
                } else {
                    System.out.printf("%s's Special Attack can't go any higher!\n", name);
                }
            }
            case SpDef -> {
                if (!(hasStatus(Moves.Status.SpDef)>5)){
                    statuses.add(Moves.Status.SpDef);
                    this.timedSpDef = 0;
                    System.out.printf("%s's Special Defense rose!\n", name);

                } else {
                    System.out.printf("%s's Special Defense can't go any higher!\n", name);
                }
            }
            case LessAtk -> {
                if (!(hasStatus(Moves.Status.LessAtk)>5)){
                    statuses.add(Moves.Status.LessAtk);
                    this.timedAtk =0;
                    System.out.printf("%s's Attack fell!\n", name);
                } else {
                    System.out.printf("%s's Attack can't go any lower!\n", name);
                }
            }
            case LessDef -> {
                if (!(hasStatus(Moves.Status.LessDef)>5)){
                    statuses.add(Moves.Status.LessDef);
                    this.timedDef = 0;
                    System.out.printf("%s's Defense fell!\n", name);
                }else {
                    System.out.printf("%s's Defense can't go any lower!\n", name);
                }
            }
            case LessSpAtk -> {
                if (!(hasStatus(Moves.Status.LessSpAtk)>5)){
                    statuses.add(Moves.Status.LessSpAtk);
                    this.timedSpAtk = 0;
                    System.out.printf("%s's SpAtk fell!\n", name);
                } else {
                    System.out.printf("%s's SpAtk can't go any lower!\n", name);
                }
            }
            case LessSpDef -> {
                if (!(hasStatus(Moves.Status.LessSpDef)>5)){
                    statuses.add(Moves.Status.LessSpDef);
                    this.timedSpDef = 0;
                    System.out.printf("%s's Special Defense fell!\n", name );
                }
                else {
                    System.out.printf("%s's Special Defense can't go any lower!\n", name);
                }
            }
            case LessSpd -> {
                if (!(hasStatus(Moves.Status.LessSpd)>5)){
                    statuses.add(Moves.Status.LessSpd);
                    this.timedSpd = 0;
                    System.out.printf("%s's Special Defense fell!\n", name);
                }
                else {
                    System.out.printf("%s's Special Defense can't go any lower!\n", name);
                }
            }
            case Par -> {
                if ((hasStatus(Moves.Status.Par)>0)||
                (hasStatus(Moves.Status.Slp)>0)||
                (hasStatus(Moves.Status.Pois)>0)||
                (hasStatus(Moves.Status.Burn)>0)){
                    System.out.printf("%s is already %s!\n", name, getStatusName());
                }
                else {

                    statuses.add(Moves.Status.Par);
                    System.out.printf("%s is now paralyzed!\n", name);
                }
            }
            case Slp -> {
                if ((hasStatus(Moves.Status.Par)>0)||
                        (hasStatus(Moves.Status.Slp)>0)||
                        (hasStatus(Moves.Status.Pois)>0)||
                        (hasStatus(Moves.Status.Burn)>0)){

                    System.out.printf("Summon is already %s\n!", getStatusName());
                }else {
                    statuses.add(Moves.Status.Slp);
                    System.out.println("Summon fell asleep!");

                }
            }
            case Pois -> {
                if ((hasStatus(Moves.Status.Par)>0)||
                        (hasStatus(Moves.Status.Slp)>0)||
                        (hasStatus(Moves.Status.Pois)>0)||
                        (hasStatus(Moves.Status.Burn)>0)){

                    System.out.printf("Summon is already %s!", getStatusName());
                }
                else {
                    statuses.add(Moves.Status.Pois);
                    System.out.println("Summon got poisoned!");
                }
            }
            case Burn -> {
                if ((hasStatus(Moves.Status.Par)>0)||
                        (hasStatus(Moves.Status.Slp)>0)||
                        (hasStatus(Moves.Status.Pois)>0)||
                        (hasStatus(Moves.Status.Burn)>0)){
                    System.out.printf("Summon is already %s!", getStatusName());
                }
                else {
                    statuses.add(Moves.Status.Burn);
                    System.out.println("Summon got burned!");

                }
            }
        }
    }

    /*
    getStatusName - getter for the name of the exclusive statuses
    @param - none
    @return - void
     */
    public String getStatusName(){
        String status = "";
        for (int i = 0; i < statuses.size(); i++) {
            switch (statuses.get(i)){
                case Par -> status = "Paralyzed";
                case Slp -> status = "Asleep";
                case Pois -> status = "Poisoned";
                case Burn -> status = "Burned";

            }
        }
        return status;
    }

    //override
    @Override
    //overrides the default toString function to make summon+hp easier
    public String toString() {
        return this.name +": " + this.hp + "/" + this.maxhp + "hp";
    }
}


