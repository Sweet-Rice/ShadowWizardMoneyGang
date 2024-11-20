import java.util.Random;


public class TurnHandler {
static Random rand = new Random();


private static Wizards you;
private static Wizards enemy;
private static Moves.Status Weather = Moves.Status.Null;
private static int turns = 1;
private static Moves aMove;
private static Moves bMove;
private static boolean moved = false;

    public static int getTurns() {
        return turns;
    }
public static Moves.Status getWeather() {
        return Weather;
}
public static void setWeather(Moves.Status weather) {
        Weather = weather;
}
    public TurnHandler(Wizards you, Wizards enemy) {
    TurnHandler.you = you;
    TurnHandler.enemy = enemy;
}

public static boolean youmoved() {
    moved = true;
    return true;

}


public static void setaMove(Moves a){
    aMove = a;

}
public static void setbMove(){
    //Enemy AI
    bMove = enemy.lead.moves.get(rand.nextInt(enemy.lead.moves.size()));
}

    public static boolean allFainted(Wizards player) {
        for (int i = 0; i < player.summons.size(); i++) {
            if (player.summons.get(i).getHp() > 0) {
                return false;  // If there's any Pokémon still standing, return false
            }
        }
        return true;  // All Pokémon have fainted
    }

public static void mainTurn() {

    //sets turn priority
    Moves moveA;
    Moves moveB;
    Summons a;
    Summons b;
    setbMove();
    // dont need to change this. You need to modify methods in Summons and maybe moves.
    if (((you.lead.getSpd()* Moves.getBuff(you.lead, Moves.Status.Spd) )* you.lead.isParalyzed())
            > ((enemy.lead.getSpd() * Moves.getBuff(you.lead, Moves.Status.Spd)* enemy.lead.isParalyzed()))) {
        a = you.lead;
        b = enemy.lead;
        moveA = aMove;
        moveB = bMove;
    } else {
        a = enemy.lead;
        b = you.lead;
        moveB = aMove;
        moveA = bMove;
    }
    //overall structure of this is okay. need to change the things inside drastically
    if (moved) {
        Moves.doMove(a, b, moveA);
        if (faintChecker()) return;

        Moves.doMove(b, a, moveB);
        if (faintChecker()) return;
    } else {
        Moves.doMove(enemy.lead, you.lead, bMove);
    }
        /*
        b.setHp(b.getHp()-getDamage(a, b, moveA));
        System.out.printf("%s did %d damage!\n", a.getName(), getDamage(a, b, moveB));

        if (faintChecker()) return;

        a.setHp(b.getHp()-getDamage(b, a, moveB));
        System.out.printf("%s did %d damage!\n", b.getName(), getDamage(b, a, moveB));
        if (faintChecker()) return;
    } else {
        you.lead.setHp(you.lead.getHp()-getDamage(enemy.lead, you.lead, bMove));
        if (faintChecker()) return;
    }
    */
        turns++;



}

    private static boolean faintChecker() {
        if (faintHandler()){
            //you fainted
            System.out.printf("%s fainted!\n", you.lead.getName());
            turns++;
            return true;
        }else if (!faintHandler()){
            //enemy fainted
            System.out.printf("%s fainted!\n", enemy.lead.getName());
            turns++;
            return true;
        }
        return false;
    }

    public static void WeatherHandler(Moves.Status status){
    switch(status){
        case Sun -> Weather = Moves.Status.Sun;
        case Rain -> Weather = Moves.Status.Rain;

        case Null -> Weather = Moves.Status.Null;
    }

}
public static double weatherMultiplier(Moves a){
   double mult = 1.0;
    switch (Weather){
       case Sun -> {
           switch (a.type){
           case Fire ->mult= 1.5;
           case Water -> mult= 0.5;
           }
       }
       case Rain -> {
           switch (a.type){
               case Fire ->mult= 0.5;
               case Water -> mult= 1.5;
           }
       }
   }
   return mult;
    }


private static Boolean faintHandler(){
    Boolean faintStatus = null;
    if (you.lead.getHp() <=0){
        you.lead.setHp(0);
        you.lead.setFainted(true);
        faintStatus = true;
}else if (enemy.lead.getHp() <= 0){
        enemy.lead.setHp(0);
        enemy.lead.setFainted(true);
        faintStatus = false;
    }
return faintStatus;
}
/*private static int getDamage(Summons attacker, Summons defender, Moves a){
    double other = 1.0; //used if passives are implemented
    double damage;
    double ADratio;

    double burnMult = 1.0;
    if (attacker.hasStatus(Moves.Status.Burn)){
        burnMult = 1.5;
    }
    double stab = 1.0;
    if ((attacker.type1 == a.type)||(attacker.type2 == a.type)){
        stab = 1.5;
    }

    damage = (44.0/50.0)*a.getBasepower();
    if (a.getDmg()== Moves.Dmg.Atk){
        ADratio = (double) attacker.getAtk() /defender.getDef();
    } else {
        ADratio = (double) attacker.getSpatk() /defender.getSpdef();
    }
    damage = damage * ADratio;
    damage += 2;
    WeatherHandler(a.getStatus());
    damage *= weatherMultiplier(a)*stab*
            (DamageCalc.getEffectiveness(a.type, defender.type1)*
                    DamageCalc.getEffectiveness(a.type, defender.type2));
    damage *= burnMult * other;
    return (int)damage;
    }*/
}
