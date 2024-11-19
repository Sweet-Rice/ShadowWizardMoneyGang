
import java.util.ArrayList;
public class Summons {



    //Summons class will serve to initialize objects that will be used as the statistics of summons

    //Name and type
    private final String name;

    public DamageCalc.Type type1;
    public DamageCalc.Type type2;

    //Stats for each summon
    private int atk;
    private int spatk;
    private int def;
    private int spdef;
    private int hp;
    private int spd;
    private ArrayList<Moves> moves;
    //damage multiplier based on type
    private final int maxhp;
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

    @Override
    public String toString() {
        String output = this.name;

        return output;
    }

}
