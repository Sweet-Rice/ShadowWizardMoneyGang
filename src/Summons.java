import java.util.List;
import java.util.ArrayList;
public class Summons {



    //Summons class will serve to initialize objects that will be used as the statistics of summons

    //Name and type
    private String name;
    public enum Type    {Bug, Dragon, Electric, Fighting,
                        Fire, Flying, Ghost, Grass, Ground,
                        Ice, Normal, Poison, Psychic, Rock, Water, Null;}
    public Type type1;
    public Type type2;

    //Stats for each summon
    private int atk;
    private int spatk;
    private int def;
    private int spdef;
    private int hp;
    private int spd;
    private List<Moves> moves;
    //damage multiplier based on type
    private double TypeMult;
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
    public Summons(String name, Type type1, Type type2,
                   int atk, int spatk, int def, int spdef, int hp, int spd){
       this.name = name;
       this.type1 = type1;
       this.type2 = type2;
       this.atk = atk;
       this.spatk = spatk;
       this.def = def;
       this.spdef = spdef;
       this.hp = hp;
       this.spd = spd;
       this.moves = new ArrayList<>();
    }




}
