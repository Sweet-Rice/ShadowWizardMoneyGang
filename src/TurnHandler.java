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



private int timedWeather = 0;

private Summons a;
private Summons b;

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
    damageStatusCheck(a);
    if (faintChecker()) return;
    damageStatusCheck(b);
    if (faintChecker()) return;
    turns++;
}
private boolean turnStart(Summons s){
    boolean huh = false;
    if (s.hasStatus(Moves.Status.Par)>0){
        System.out.printf("%s is Paralyzed!\n", s.getName());
        s.timedPar++;
        if (s.timedPar ==10){
            System.out.printf("%s stopped being paralyzed!", s.getName());
            s.removeStatus(Moves.Status.Par);
            s.timedPar = 0;
        }
        int i = rand.nextInt(4);
        System.out.println(i);
        if ((i==3)){
            huh=true;
            System.out.print("It can't move!\n");
        }
    }
    else if(s.hasStatus(Moves.Status.Slp)>0){
        System.out.printf("%s is fast Asleep\n!", s.getName());
    s.timedSlp++;
    if (s.timedSlp==3){
        System.out.println("It woke up!");
        s.removeStatus(Moves.Status.Slp);
        s.timedSlp=0;
    }else{huh= true;}

    }
    return huh;
}
private void turnEnd(){
//Weather check
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

    //Stat mod check
    statModCheck(a);
    //Stat Mod Check
    statModCheck(b);
    //damage status check

}
private void damageStatusCheck(Summons s){
    if (s.hasStatus(Moves.Status.Burn)>0){
        s.timedBurn++;
        if (s.timedBurn==10){
            System.out.printf("%s healed its burn!\n", s.getName());
        }
        else {
            s.setHp(s.getHp()-(int)(s.getMaxHp()*(1.0/16.0)));
            System.out.printf("%s was hurt by its burn!\n", s.getName());
        }

    }
    if (s.hasStatus(Moves.Status.Pois)>0){
        s.timedPois++;
        if (s.timedPois==10){
            System.out.printf("%s healed its pois!\n", s.getName());
        }
        else {
            s.setHp(s.getHp()-(int)(s.getMaxHp()*(1.0/8.0)));
            System.out.printf("%s was hurt by its poison!\n", s.getName());
        }
    }

}

private void statModCheck(Summons s){
    if (s.getBuff(Moves.Status.Spd)>0){
        s.timedSpd++;
        if (s.timedSpd ==6){
            for (int i = 0; i<s.statuses.size(); i++){
                if (s.statuses.get(i).equals(Moves.Status.Spd)){
                    s.statuses.remove(i);
                }
            }
            for (int i =0; i<s.statuses.size(); i++){
                if (s.statuses.get(i).equals(Moves.Status.LessSpd)){
                    s.statuses.remove(i);
                }
            }
            s.timedSpd=0;
        }

    }
    if (s.getBuff(Moves.Status.Atk)>0){
       s.timedAtk++;
       if (s.timedAtk ==6){
           for (int i = 0; i<s.statuses.size(); i++){
               if (s.statuses.get(i).equals(Moves.Status.Atk)){
                   s.statuses.remove(i);
               }
           }
           for(int i =0; i<s.statuses.size(); i++){
               if (s.statuses.get(i).equals(Moves.Status.LessAtk)){
                   s.statuses.remove(i);
               }
           }
           s.timedAtk=0;
       }
    }
    if (s.getBuff(Moves.Status.SpAtk)>0){
        s.timedSpAtk++;
        if (s.timedSpAtk ==6){
            for (int i = 0; i<s.statuses.size(); i++){
                if (s.statuses.get(i).equals(Moves.Status.SpAtk)){
                    s.statuses.remove(i);
                }
            }
            for(int i =0; i<s.statuses.size(); i++){
                if (s.statuses.get(i).equals(Moves.Status.LessSpAtk)){
                    s.statuses.remove(i);

                }
            }
            s.timedSpAtk=0;
        }

    }
    if (s.getBuff(Moves.Status.Def)>0){
        s.timedDef++;
        if (s.timedDef ==6){
            for (int i = 0; i<s.statuses.size(); i++){
                if (s.statuses.get(i).equals(Moves.Status.Def)){
                    s.statuses.remove(i);

                }
            }
            for (int i =0; i<s.statuses.size(); i++){
                if (s.statuses.get(i).equals(Moves.Status.LessDef)){
                    s.statuses.remove(i);
                }
            }
            s.timedDef=0;
        }
    }
    if (s.getBuff(Moves.Status.SpDef)>0){
        s.timedSpDef++;
        if (s.timedSpDef ==6){
            for (int i = 0; i<s.statuses.size(); i++){
                if (s.statuses.get(i).equals(Moves.Status.SpDef)){
                    s.statuses.remove(i);

                }
            }
            for (int i =0; i<s.statuses.size(); i++){
                if (s.statuses.get(i).equals(Moves.Status.LessSpDef)){
                    s.statuses.remove(i);
                }
            }
            s.timedSpDef=0;
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
