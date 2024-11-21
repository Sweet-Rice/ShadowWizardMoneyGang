import java.util.Random;


public class TurnHandler {
 Random rand = new Random();
private Game game;

private Wizards you;
private Wizards enemy;
private Moves.Status Weather = Moves.Status.Null;
private int turns = 1;
private  Moves aMove;
private  Moves bMove;
private  boolean moved = false;
private int timedSlp = 0;
private int timedWeather = 0;

public TurnHandler(Game game, Wizards you, Wizards enemy) {
        this.you = you;
        this.enemy = enemy;
        this.game = game;
    }
public void toRainOrNotToRain(Summons user, Summons nonUser, Moves move) {
        switch (move.getStatus()){
            case Sun -> {
                setWeather(Moves.Status.Sun);
                System.out.println("The sun grew bright!");
            }
            case Rain -> {
                setWeather(Moves.Status.Rain);
                System.out.println("The rain came pouring!");
            }default -> {
                switch (move.getSelf()){
                    case Opposite, Drain, Recoil -> nonUser.addStatuses(move.getStatus());
                    case Self -> user.addStatuses(move.getStatus());
                    case Both -> {
                        nonUser.addStatuses(move.getStatus());
                        user.addStatuses(move.getStatus());
                    }
                }

            }
        }
    }
public void mainTurn() {
    System.out.print("\nTurn " + turns);
    //sets turn priority
    Moves moveA;
    Moves moveB;
    Summons a;
    Summons b;
    setbMove();
    // dont need to change this. You need to modify methods in Summons and maybe moves.
    if (((you.lead.getSpd()* you.lead.getBuff( Moves.Status.Spd) )* you.lead.isParalyzed())
            > ((enemy.lead.getSpd() * you.lead.getBuff( Moves.Status.Spd)* enemy.lead.isParalyzed()))) {
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
        if (!turnStart(a)) {
            moveA.doMove(a, b);
            weatherModifier(moveA, a, b);
            DamageCalc get = new DamageCalc(moveA, b);
            double Eff = get.getEffectiveness() * get.getEffectiveness2();
            //debug line
            getEff(moveA, b, a, get, Eff);
            if (faintChecker()) return;
        }
        if (!turnStart(b)){
            moveB.doMove(b, a);
            DamageCalc get = new DamageCalc(moveB, a);
            double Eff = get.getEffectiveness() * get.getEffectiveness2();
            //debug line
            getEff(moveB, b, a, get, Eff);
            if (faintChecker()) return;
        }

    } else {
        if (turnStart(enemy.lead)) {
            bMove.doMove(enemy.lead, you.lead);
            DamageCalc get = new DamageCalc(bMove, you.lead);
            double Eff = get.getEffectiveness() * get.getEffectiveness2();
            getEff(bMove, enemy.lead, you.lead, get, Eff);
            weatherModifier(bMove, enemy.lead, you.lead);
            toRainOrNotToRain(enemy.lead, you.lead, bMove);
            if (faintChecker()) return;
        }

    }
    turnEnd();
    turns++;
}
private boolean turnStart(Summons s){
    boolean huh = false;
    if (s.hasStatus(Moves.Status.Par)>0){
        System.out.printf("%s is Paralyzed!\n", s.getName());
        int i = rand.nextInt(4);
        System.out.println(i);
        if ((i==3)){
            huh=true;
            System.out.print("It can't move!\n");
        }
    }else if(s.hasStatus(Moves.Status.Slp)>0){
        System.out.printf("%s is fast Asleep\n!", s.getName());
    timedSlp++;
    if (timedSlp==3){
        System.out.println("It woke up!");
        s.removeStatus(Moves.Status.Slp);
        timedSlp=0;
    }else{huh= true;}

    }
    return huh;
}
private void turnEnd(){
if (Weather != Moves.Status.Null){
    if (timedWeather == 5){
        switch (Weather){
            case Rain : System.out.println("The rain stopped pouring.");
            case Sun : System.out.println("The sun stopped beating.");
            default: setWeather(Moves.Status.Null);
        }
    }else {
        switch (Weather){
            case Rain -> {
                System.out.println("The rain floods the battlefield!");
            }
            case Sun -> {
                System.out.println("The sun beats down on the battlefield!");
            }
        }
        timedWeather++;
    }

}
}
private void getEff(Moves moveB, Summons a, Summons b, DamageCalc get, double eff) {
        System.out.printf("type1: %f type2: %f Eff: %f\n", get.getEffectiveness(), get.getEffectiveness2(), eff);

        if (eff <1){
            System.out.println("it was not very effective...");
        } else if (eff >1){
            System.out.println("It was super effective!");
        }
        get = null;
        toRainOrNotToRain(b, a, moveB);

    }
public int getTurns() {
        return turns;
    }
public Moves.Status getWeather() {
        return Weather;
}
public void setWeather(Moves.Status weather) {
    switch(weather){
        case Sun -> Weather = Moves.Status.Sun;
        case Rain -> Weather = Moves.Status.Rain;

        case Null -> Weather = Moves.Status.Null;
    }
}
public boolean youmoved() {
    moved = true;
    return true;

}
public void setaMove(Moves a){
    aMove = a;

}
public  void setbMove(){
    //Enemy AI
    bMove = enemy.lead.moves.get(rand.nextInt(enemy.lead.moves.size()));
    System.out.printf("");
}
public  boolean allFainted(Wizards player) {
        for (int i = 0; i < player.summons.size(); i++) {
            if (player.summons.get(i).getHp() > 0) {
                return false;  // If there's any Pokémon still standing, return false
            }
        }
        return true;  // All Pokémon have fainted
    }
private boolean faintChecker() {
        if (faintHandler()==1){
            //you fainted
            System.out.printf("\n%s fainted!\n\n", you.lead.getName());
            turns++;
            return true;
        }else if (faintHandler()==2){
            //enemy fainted
            System.out.printf("\n%s fainted!\n\n", enemy.lead.getName());
            turns++;
            return true;
        }
        return false;
    }
public  void weatherModifier(Moves moveA, Summons a, Summons b){

    switch (Weather){
       case Sun -> {
           switch (moveA.type){
           case Fire ->{
               switch (moveA.getSelf()){
                   case Opposite, Drain, Recoil -> {
                       b.setHp((b.getHp()-(int)((0.5)*(moveA.damageCalc(a,b)))));
                   }
                   case Self -> a.setHp((a.getHp()-(int)((0.01)*(moveA.getBasepower())*(a.getMaxHp()))));
                   case Both -> {
                       a.setHp((a.getHp()-(int)((0.5)*(moveA.damageCalc(a,b)))));
                       b.setHp((b.getHp()-(int)((0.5)*(moveA.damageCalc(a,b)))));
                   }
               }
           }
           case Water -> {
               switch (moveA.getSelf()){
                   case Opposite, Drain, Recoil -> {
                       b.setHp((b.getHp()+(int)((0.5)*(moveA.damageCalc(a,b)))));
                   }
                   case Self -> a.setHp((a.getHp()-(int)((0.01)*(moveA.getBasepower())*(a.getMaxHp()))));
                   case Both -> {
                       a.setHp((a.getHp()+(int)((0.5)*(moveA.damageCalc(a,b)))));
                       b.setHp((b.getHp()+(int)((0.5)*(moveA.damageCalc(a,b)))));
                   }
               }
           }
           }
       }
       case Rain -> {
           switch (moveA.type){
               case Water ->{
                   switch (moveA.getSelf()){
                       case Opposite, Drain, Recoil -> {
                           b.setHp((b.getHp()-(int)((0.5)*(moveA.damageCalc(a,b)))));
                       }
                       case Self -> a.setHp((a.getHp()-(int)((0.01)*(moveA.getBasepower())*(a.getMaxHp()))));
                       case Both -> {
                           a.setHp((a.getHp()-(int)((0.5)*(moveA.damageCalc(a,b)))));
                           b.setHp((b.getHp()-(int)((0.5)*(moveA.damageCalc(a,b)))));
                       }
                   }
               }
               case Fire -> {
                   switch (moveA.getSelf()){
                       case Opposite, Drain, Recoil -> {
                           b.setHp((b.getHp()+(int)((0.5)*(moveA.damageCalc(a,b)))));
                       }
                       case Self -> a.setHp((a.getHp()-(int)((0.01)*(moveA.getBasepower())*(a.getMaxHp()))));
                       case Both -> {
                           a.setHp((a.getHp()+(int)((0.5)*(moveA.damageCalc(a,b)))));
                           b.setHp((b.getHp()+(int)((0.5)*(moveA.damageCalc(a,b)))));
                       }
                   }
               }
           }
       }

   }

    }
private int faintHandler(){
    int faintStatus = 0;
    if (you.lead.getHp() <=0){
        you.lead.setHp(0);
        you.lead.setFainted(true);
        faintStatus = 1;
}else if (enemy.lead.getHp() <= 0){
        enemy.lead.setHp(0);
        enemy.lead.setFainted(true);
        faintStatus = 2;
    }
return faintStatus;
}

}
