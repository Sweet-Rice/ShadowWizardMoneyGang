
import java.util.ArrayList;


public class Summons {



    //Summons class will serve to initialize objects that will be used as the statistics of summons

    //Name and type
    private final String name;

    public DamageCalc.Type type1;
    public DamageCalc.Type type2;

    //Stats for each summon
    private boolean fainted = false;
    private final int atk;
    private final int spatk;
    private final int def;
    private final int spdef;
    private int hp;
    private final int spd;
    public ArrayList<Moves> moves;
    //damage multiplier based on type
    private final int maxhp;
    private Moves.Status status = Moves.Status.Null;
    public ArrayList<Moves.Status> statuses;



    public Summons(String name, DamageCalc.Type type1, DamageCalc.Type type2,
                   int atk, int spatk, int def, int spdef, int maxhp, int spd){
        /*
    Methodname Summons - serves as a constructor method to define all stats of each summon.
    @param name - name of the summon
    @param type1 - first type of the summon
    @param type2 - second type of summon
    @param atk - attack value of the summon
    @param spatk - special attack value of the summon
    @param def - defense value of the summon
    @param spdef - special defense value of the summon
    @param hp - health points of the summon
    @param spd - speed value of the summon

     */
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



    //ORGANIZATION!!!!!!
    //getter functions

    public String getName(){
        return name;
    }
    public int getAtk(){
        return atk;
    }
    public int getSpatk(){
        return spatk;
    }
    public int getDef(){
        return def;
    }
    public int getSpdef(){
        return spdef;
    }
    public int getSpd(){
        return spd;
    }
    public int getHp(){
        return hp;
    }
    public  int getMaxHp(){
        return maxhp;
    }
    public int hasStatus(Moves.Status status){
        int x = 0;
        for(int i = 0; i < statuses.size(); i++){
            if(statuses.get(i) == status){
                x++;
            }
        }
        return x;
    }
    public boolean isFainted(){
        return fainted;
    }
    public double isParalyzed(){
        double paralyzed = 1.0;
        if (status == Moves.Status.Par) {
            paralyzed = 0.5;
        }
        return paralyzed;
    }


    //setter functions!!!!
    public void setHp(int hp){
        this.hp = hp;
    }
    public void setFainted(boolean fainted){
        this.fainted = fainted;
    }

    // genuine utility
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
    public void addMove(Moves move){
        moves.add(move);
    }
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
                    System.out.println("Attack rose!");
                }else {
                    System.out.println("Attack can't go any higher!");
                }
            }
            case Def -> {
                if (!(hasStatus(Moves.Status.Def)>5)){
                    statuses.add(Moves.Status.Def);
                    System.out.println("Defense rose!");
                } else {
                    System.out.println("Defense can't go any higher!");
                }
            }
            case Spd -> {
                if (!(hasStatus(Moves.Status.Spd)>5)){
                    statuses.add(Moves.Status.Spd);
                    System.out.println("Speed rose!");
                } else {
                    System.out.println("Speed can't go any higher!");
                }
            }
            case SpAtk -> {
                if (!(hasStatus(Moves.Status.SpAtk)>5)){
                    statuses.add(Moves.Status.SpAtk);
                    System.out.println("Special Attack rose!");
                } else {
                    System.out.println("Special Attack can't go any higher!");
                }
            }
            case SpDef -> {
                if (!(hasStatus(Moves.Status.SpDef)>5)){
                    statuses.add(Moves.Status.SpDef);
                    System.out.println("Special Defense rose!");

                } else {
                    System.out.println("Special Defense can't go any higher!");
                }
            }
            case LessAtk -> {
                if (!(hasStatus(Moves.Status.LessAtk)>5)){
                    statuses.add(Moves.Status.LessAtk);
                    System.out.println("Attack fell!");
                } else {
                    System.out.println("Attack can't go any lower!");
                }
            }
            case LessDef -> {
                if (!(hasStatus(Moves.Status.LessDef)>5)){
                    statuses.add(Moves.Status.LessDef);
                    System.out.println("Defense fell!");
                }else {
                    System.out.println("Defense can't go any lower!");
                }
            }
            case LessSpAtk -> {
                if (!(hasStatus(Moves.Status.LessSpAtk)>5)){
                    statuses.add(Moves.Status.LessSpAtk);
                    System.out.println("SpAtk fell!");
                } else {
                    System.out.println("SpAtk can't go any lower!");
                }
            }
            case LessSpDef -> {
                if (!(hasStatus(Moves.Status.LessSpDef)>5)){
                    statuses.add(Moves.Status.LessSpDef);
                    System.out.println("Special Defense fell!");
                }
                else {
                    System.out.println("Special Defense can't go any lower!");
                }
            }
            case LessSpd -> {
                if (!(hasStatus(Moves.Status.LessSpd)>5)){
                    statuses.add(Moves.Status.LessSpd);
                    System.out.println("Special Defense fell!");
                }
                else {
                    System.out.println("Special Defense can't go any lower!");
                }
            }
            case Par -> {
                if (!(hasStatus(Moves.Status.Par)>0)){
                    statuses.add(Moves.Status.Par);
                    System.out.println("Summon is now paralyzed!");
                }
                else {
                    System.out.println("Summon is already paralyzed!");
                }
            }
            case Slp -> {
                if (!(hasStatus(Moves.Status.Slp)>0)){
                    statuses.add(Moves.Status.Slp);
                    System.out.println("Summon fell asleep!");
                }else {
                    System.out.println("Summon is already asleep!");
                }
            }
            case Pois -> {
                if (!(hasStatus(Moves.Status.Pois)>0)){
                    statuses.add(Moves.Status.Pois);
                    System.out.println("Summon got poisoned!");
                }
                else {
                    System.out.println("Summon is already poisoned!");
                }
            }
            case Burn -> {
                if (!(hasStatus(Moves.Status.Burn)>0)){
                    statuses.add(Moves.Status.Burn);
                    System.out.println("Summon got burned!");
                }
                else {
                    System.out.println("Summon is already burned!");
                }
            }
        }
    }

    //override
    @Override
    public String toString() {
        return this.name +": " + this.hp + "/" + this.maxhp + "hp";
    }
}


