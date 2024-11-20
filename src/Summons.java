
import java.util.ArrayList;
import java.util.Objects;

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
    public Moves.Status status = Moves.Status.Null;
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
    @param moves - list of moves the summon has
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
    }
    public void addMove(Moves move){
        moves.add(move);
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
    public void setHp(int hp){
        this.hp = hp;
    }
    public boolean isFainted(){
        return fainted;
    }
    public void setFainted(boolean fainted){
        this.fainted = fainted;
    }
    public String getName(){
        return name;
    }
    public double isParalyzed(){
        double paralyzed = 1.0;
        if (status == Moves.Status.Par) {
            paralyzed = 0.5;
        }
        return paralyzed;
    }
    @Override
    public String toString() {
        return this.name +": " + this.hp + "/" + this.maxhp + "hp";
    }

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
}
