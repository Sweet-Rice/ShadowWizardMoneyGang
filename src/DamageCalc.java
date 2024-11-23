public class DamageCalc {
   public enum Type {Normal, Fire, Water, Electric, Grass, Ice, Fighting, Poison, Ground, Fly, Psychic, Bug, Rock, Ghost, Dragon, Null}
    private Moves attack;
   private Summons defender;

    public static final double[][] Effectiveness = {
            //              Normal, Fire, Water, Electric, Grass, Ice, Fighting, Poison, Ground, Fly, Psychic, Bug, Rock, Ghost, Dragon, Null
            /* Normal   */{    1.0,  1.0,   1.0,      1.0,   1.0, 1.0,      1.0,    1.0,    1.0, 1.0,     1.0, 1.0,  0.5,   0.0,    1.0, 1.0},
            /* Fire     */{    1.0,  0.5,   0.5,      1.0,   2.0, 2.0,      1.0,    1.0,    1.0, 1.0,     1.0, 2.0,  0.5,   1.0,    1.0, 0.5},
            /* Water    */{    1.0,  2.0,   0.5,      1.0,   0.5, 1.0,      1.0,    1.0,    2.0, 1.0,     1.0, 1.0,  2.0,   1.0,    0.5, 1.0},
            /* Electric */{    1.0,  1.0,   2.0,      0.5,   0.5, 1.0,      1.0,    1.0,    0.0, 2.0,     1.0, 1.0,  0.5,   0.0,    0.5, 1.0},
            /* Grass    */{    1.0,  0.5,   2.0,      1.0,   0.5, 1.0,      1.0,    0.5,    2.0, 1.0,     1.0, 0.5,  2.0,   1.0,    0.5, 1.0},
            /* Ice      */{    1.0,  1.0,   0.5,      1.0,   2.0, 0.5,      1.0,    1.0,    2.0, 2.0,     1.0, 1.0,  1.0,   1.0,    1.0, 2.0},
            /* Fighting */{    2.0,  1.0,   1.0,      1.0,   1.0, 1.0,      1.0,    1.0,    1.0, 0.5,     0.5, 0.5,  2.0,   0.0,    1.0, 1.0},
            /* Poison   */{    1.0,  1.0,   1.0,      1.0,   2.0, 1.0,      1.0,    0.5,    0.5, 1.0,     1.0, 2.0,  0.5,   0.5,    1.0, 1.0},
            /* Ground   */{    1.0,  2.0,   1.0,      2.0,   0.5, 1.0,      1.0,    2.0,    1.0, 0.0,     1.0, 0.5,  2.0,   1.0,    1.0, 1.0},
            /* Flying   */{    1.0,  1.0,   1.0,      0.5,   2.0, 1.0,      2.0,    1.0,    1.0, 1.0,     1.0, 2.0,  0.5,   1.0,    1.0, 1.0},
            /* Psychic  */{    1.0,  1.0,   1.0,      1.0,   1.0, 1.0,      2.0,    2.0,    1.0, 1.0,     0.5, 1.0,  1.0,   1.0,    1.0, 1.0},
            /* Bug      */{    1.0,  0.5,   1.0,      1.0,   2.0, 1.0,      0.5,    2.0,    1.0, 1.0,     2.0, 1.0,  1.0,   0.5,    1.0, 1.0},
            /* Rock     */{    1.0,  2.0,   1.0,      1.0,   1.0, 2.0,      0.5,    1.0,    0.5, 2.0,     1.0, 2.0,  1.0,   1.0,    1.0, 1.0},
            /* Ghost    */{    0.0,  1.0,   1.0,      1.0,   1.0, 1.0,      1.0,    1.0,    1.0, 1.0,     0.0, 1.0,  1.0,   2.0,    1.0, 1.0},
            /* Dragon   */{    1.0,  1.0,   1.0,      1.0,   1.0, 1.0,      1.0,    1.0,    1.0, 1.0,     1.0, 1.0,  1.0,   1.0,    1.0, 1.0},
            /* Null     */{    1.0,  1.0,   1.0,      1.0,   1.0, 1.0,      1.0,    1.0,    1.0, 1.0,     1.0, 1.0,  1.0,   1.0,    2.0, 1.0}
    };

    /*
    getEffectiveness - provides the effectiveness of the attack on the defender's first type
    @param - none
    @return - the damage multiplier
     */
   public double getEffectiveness() {
       return Effectiveness[attack.type.ordinal()][defender.getType1().ordinal()];
   }
    /*
    getEffectiveness1 - provides the effectiveness of the attack on the defender's second type
    @param - none
    @return - the damage multiplier
     */
   public double getEffectiveness2() {
       return  Effectiveness[attack.type.ordinal()][defender.getType2().ordinal()];
   }

    /*
    DamageCalc - constructor
    @attacker - provides DamageCalc access to the object of the Move class
    @defender - provides DamageCalc access to the object of the Summons class
     */
    public DamageCalc(Moves attacker, Summons defender) {
        this.attack = attacker;
        this.defender = defender;
    }

}

