import java.util.Random;


public class TurnHandler {
static Random rand = new Random();


private static Wizards you;
private static Wizards enemy;
private static Moves.Status Weather;
private static int turns = 0;
private static Moves aMove;
private static Moves bMove;
private static boolean moved = false;

public TurnHandler(Wizards you, Wizards enemy) {
    this.you = you;
    this.enemy = enemy;
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

public static void mainTurn() {
    //sets turn priority
    Moves moveA;
    Moves moveB;
    Summons a;
    Summons b;
    setbMove();
    if ((you.lead.getSpd()*you.lead.isParalyzed())>(enemy.lead.getSpd()*enemy.lead.isParalyzed())) {
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
    if (moved){
    b.setHp(b.getHp()-getDamage(a, b, moveA));
    System.out.printf("%s did %d damage!", a.getName(), getDamage(a, b, moveB));
    if (faintHandler()){
        //you fainted
        System.out.println("You fainted");
    }else if (!faintHandler()){
        //enemy fainted
        System.out.println("Enemy fainted");
    }
    a.setHp(b.getHp()-getDamage(b, a, bMove));
        System.out.printf("%s did %d damage!", b.getName(), getDamage(b, a, bMove));
    }
    if (faintHandler()){
        //you fainted
        System.out.println("You fainted");
    }else if (!faintHandler()){
        //enemy fainted
        System.out.println("Enemy fainted");
    }
    turns++;



}
private static void WeatherHandler(Moves.Status status){
    switch(status){
        case Sun -> Weather = Moves.Status.Sun;
        case Rain -> Weather = Moves.Status.Rain;

        default -> Weather = Moves.Status.Null;
    }

}
private static double weatherMultiplier(Moves a){
   double mult = 1.0;
    switch (Weather){
       case Sun -> {
           switch (a.type){
           case Fire ->mult= 1.5;
           case Water -> mult= 0.5;
           default -> mult = 1.0;
       }
       }
       case Rain -> {
           switch (a.type){
               case Fire ->mult= 0.5;
               case Water -> mult= 1.5;
               default -> mult = 1.0;
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
private static int getDamage(Summons attacker, Summons defender, Moves a){
    double other = 1.0; //used if passives are implemented
    double damage;
    double ADratio;

    double burnMult = 1.0;
    if (attacker.status == Moves.Status.Burn){
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
    }
}
