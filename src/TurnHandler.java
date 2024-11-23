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

    /*
        TurnHandler - constructor
        @game - game object for method usage
        @you -  you object for method usage and player reference
        @enemy - enemy object for method usage and enemy reference
         */

public TurnHandler(Game game, Wizards you, Wizards enemy) {
        this.you = you;
        this.enemy = enemy;
        this.game = game;
    }

    /*
    toRainorNotToRain - checks if it should Rain or be Sunny or be Null, if not it applies status
    @user - the user of the move
    @nonUser - the nonuser of the move
    @move - the move being used
    @return - void
     */
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

    /*
    mainturn - what happens in a single turn
    @param - none
    @return - void
     */
public void mainTurn() {
    System.out.println("__________________________________________________________________________________________________________");

    System.out.print("\nTurn " + turns);
    //sets turn priority
    Moves moveA;
    Moves moveB;

    setbMove();
    // dont need to change this. You need to modify methods in Summons and maybe moves.
    if (((you.getLead().getSpd()* you.getLead().getBuff( Moves.Status.Spd) )* you.getLead().isParalyzed())
            > ((enemy.getLead().getSpd() * you.getLead().getBuff( Moves.Status.Spd)* enemy.getLead().isParalyzed()))) {
        a = you.getLead();
        b = enemy.getLead();
        moveA = aMove;
        moveB = bMove;
    } else {
        a = enemy.getLead();
        b = you.getLead();
        moveB = aMove;
        moveA = bMove;
    }
    //overall structure of this is okay. need to change the things inside drastically
    if (moved) {
        if (!turnStart(a)) {
            System.out.printf("\n%s used %s!\n", a.getName(), moveA.getName());
            moveA.doMove(a, b);

            weatherModifier(moveA, a, b);
            DamageCalc get = new DamageCalc(moveA, b);
            double Eff = get.getEffectiveness() * get.getEffectiveness2();
            //debug line
            getEff(get, Eff);
            toRainOrNotToRain(a, b, moveA);
            if (faintChecker()) return;
        }
        if (!turnStart(b)){
            System.out.printf("\n%s used %s!\n", b.getName(), moveB.getName());
            moveB.doMove(b, a);

            DamageCalc get = new DamageCalc(moveB, a);
            double Eff = get.getEffectiveness() * get.getEffectiveness2();
            //debug line
            getEff(get,  Eff);
            toRainOrNotToRain(a, b, moveA);
            if (faintChecker()) return;

        }

    } else {
        if (turnStart(enemy.getLead())) {
            System.out.printf("\n%s used %s!", enemy.getName(), bMove.getName());
            bMove.doMove(enemy.getLead(), you.getLead());

            DamageCalc get = new DamageCalc(bMove, you.getLead());
            double Eff = get.getEffectiveness() * get.getEffectiveness2();
            getEff( get, Eff);
            weatherModifier(bMove, enemy.getLead(), you.getLead());
            toRainOrNotToRain(enemy.getLead(), you.getLead(), bMove);
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

    /*
        turnStart - things that are checked and updated at the start of the turn for a summon
        @s - the summon being checked
        @return - whether the summon can move or not
         */
private boolean turnStart(Summons s){
    boolean huh = false;
    if (s.hasStatus(Moves.Status.Par)>0){
        System.out.printf("%s is Paralyzed!\n", s.getName());
        s.statTimerTick(Moves.Status.Par);
        if (s.getStatTimer(Moves.Status.Par) ==10){
            System.out.printf("%s stopped being paralyzed!", s.getName());
            s.removeStatus(Moves.Status.Par);
            s.statTimerZero(Moves.Status.Par);
        }
        int i = rand.nextInt(4);
        System.out.println(i);
        if ((i==3)){
            huh=true;
            System.out.printf("%s can't move!\n", s.getName());
        }
    }
    else if(s.hasStatus(Moves.Status.Slp)>0){
        System.out.printf("%s is fast Asleep\n!", s.getName());
    s.statTimerTick(Moves.Status.Slp);
    if (s.getStatTimer(Moves.Status.Slp)==3){
        System.out.printf("%s woke up!\n", s.getName());
        s.removeStatus(Moves.Status.Slp);
        s.statTimerZero(Moves.Status.Slp);
    }else {
        huh = true;
        System.out.printf("%s kept snoring!\n", s.getName());
    }
    }
    return huh;
}

    /*
        turnEnd - all the things that get checked and updated at the end of a turn
        @param -none
        @return - void
         */
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
    /*
        damageStatusCheck - updates the damage that should take place if Burn or Poison is active
        @s - the summon to be checked
        @return - void
         */
private void damageStatusCheck(Summons s){
    if (s.hasStatus(Moves.Status.Burn)>0){
        s.statTimerTick(Moves.Status.Burn);
        if (s.getStatTimer(Moves.Status.Burn)==10){
            System.out.printf("%s healed its burn!\n", s.getName());
            s.removeStatus(Moves.Status.Burn);
            s.statTimerZero(Moves.Status.Burn);
        }
        else {
            s.setHp(s.getHp()-(int)(s.getMaxHp()*(1.0/16.0)));
            System.out.printf("%s was hurt by its burn!\n", s.getName());
        }

    }
    if (s.hasStatus(Moves.Status.Pois)>0){
        s.statTimerTick(Moves.Status.Pois);
        if (s.getStatTimer(Moves.Status.Pois)==10){
            System.out.printf("%s healed its pois!\n", s.getName());
            s.removeStatus(Moves.Status.Pois);
            s.statTimerZero(Moves.Status.Pois);
        }
        else {
            s.setHp(s.getHp()-(int)(s.getMaxHp()*(1.0/8.0)));
            System.out.printf("%s was hurt by its poison!\n", s.getName());
        }
    }

}

    /*
        statModCheck - checks the statmodtimers and reacts accordingly
        @s - summon to be checked and updated
        @return - void
         */
private void statModCheck(Summons s){
    if (s.getBuff(Moves.Status.Spd)>0){
        s.statTimerTick(Moves.Status.Spd);
        if (s.getStatTimer(Moves.Status.Spd) ==6){
            for (int i = 0; i<s.getStatuses().size(); i++){
                if (s.getStatuses().get(i).equals(Moves.Status.Spd)){
                    s.getStatuses().remove(i);
                }
            }
            for (int i =0; i<s.getStatuses().size(); i++){
                if (s.getStatuses().get(i).equals(Moves.Status.LessSpd)){
                    s.getStatuses().remove(i);
                }
            }
            s.statTimerZero(Moves.Status.Spd);
        }

    }
    if (s.getBuff(Moves.Status.Atk)>0){
       s.statTimerTick(Moves.Status.Atk);
       if (s.getStatTimer(Moves.Status.Atk) ==6){
           for (int i = 0; i<s.getStatuses().size(); i++){
               if (s.getStatuses().get(i).equals(Moves.Status.Atk)){
                   s.getStatuses().remove(i);
               }
           }
           for(int i =0; i<s.getStatuses().size(); i++){
               if (s.getStatuses().get(i).equals(Moves.Status.LessAtk)){
                   s.getStatuses().remove(i);
               }
           }
           s.statTimerZero(Moves.Status.Atk);

       }
    }
    if (s.getBuff(Moves.Status.SpAtk)>0){
        s.statTimerTick(Moves.Status.SpAtk);
        if (s.getStatTimer(Moves.Status.SpAtk) ==6){
            for (int i = 0; i< s.getStatuses().size(); i++){
                if (s.getStatuses().get(i).equals(Moves.Status.SpAtk)){
                    s.getStatuses().remove(i);
                }
            }
            for(int i =0; i<s.getStatuses().size(); i++){
                if (s.getStatuses().get(i).equals(Moves.Status.LessSpAtk)){
                    s.getStatuses().remove(i);

                }
            }
            s.statTimerZero(Moves.Status.SpAtk);
        }

    }
    if (s.getBuff(Moves.Status.Def)>0){
        s.statTimerTick(Moves.Status.Def);
        if (s.getStatTimer(Moves.Status.Def) ==6){
            for (int i = 0; i<s.getStatuses().size(); i++){
                if (s.getStatuses().get(i).equals(Moves.Status.Def)){
                    s.getStatuses().remove(i);

                }
            }
            for (int i =0; i<s.getStatuses().size(); i++){
                if (s.getStatuses().get(i).equals(Moves.Status.LessDef)){
                    s.getStatuses().remove(i);
                }
            }
            s.statTimerZero(Moves.Status.Def);
        }
    }
    if (s.getBuff(Moves.Status.SpDef)>0){
        s.statTimerTick(Moves.Status.SpDef);
        if (s.getStatTimer(Moves.Status.SpDef) ==6){
            for (int i = 0; i<s.getStatuses().size(); i++){
                if (s.getStatuses().get(i).equals(Moves.Status.SpDef)){
                    s.getStatuses().remove(i);

                }
            }
            for (int i =0; i<s.getStatuses().size(); i++){
                if (s.getStatuses().get(i).equals(Moves.Status.LessSpDef)){
                    s.getStatuses().remove(i);
                }
            }
            s.statTimerZero(Moves.Status.SpDef);
        }
    }

}

    /*
        getEff -prints the effectiveness of a move
        @get - the DamageCalc object
        @eff - the effectiveness as a double
        @return - void
         */
private void getEff( DamageCalc get, double eff) {
        //System.out.printf("type1: %f type2: %f Eff: %f\n", get.getEffectiveness(), get.getEffectiveness2(), eff);

        if (eff <1){
            System.out.println("it was not very effective...");
        } else if (eff >1){
            System.out.println("It was super effective!");
        }
        get = null;


    }

    /*
    setWeather - sets the weather
    @weather - weather to be set
    @return - void
     */
public void setWeather(Moves.Status weather) {
    switch(weather){
        case Sun -> Weather = Moves.Status.Sun;
        case Rain -> Weather = Moves.Status.Rain;

        case Null -> Weather = Moves.Status.Null;
    }
}
    /*
        youmoved - sets moved to true and returns true
        @param - none
        @return - true
         */
public boolean youmoved() {
    moved = true;
    return true;

}
    /*
        setaMove - sets the mvoe for the summon that moves first
        @a - the move of the summon that goes first
        @return - void
         */
public void setaMove(Moves a){
    aMove = a;

}
    /*
        setbMove - sets the mopve for the summon that moves second
        @a - the move of the summon that goes second
        @return - void
         */
public  void setbMove(){
    //Enemy AI
    bMove = enemy.getLead().getMoves().get(rand.nextInt(enemy.getLead().getMoves().size()));
    System.out.printf("");
}
    /*
        allFainted - checks if all a wizard's summons are fainted
        @player - the wizard to be checked
        @return - all fainted or not
         */
public  boolean allFainted(Wizards player) {
        for (int i = 0; i < player.getSummons().size(); i++) {
            if (player.getSummons().get(i).getHp() > 0) {
                return false;  // If there's any Pokémon still standing, return false
            }
        }
        return true;  // All Pokémon have fainted
    }
    /*
    faintChecker - checks if you or enemy's lead summon is fainted
    @param - none
    @return - if fainted or not
     */
private boolean faintChecker() {
        if (faintHandler()==1){
            //you fainted
            System.out.printf("\n%s fainted!\n\n", you.getLead().getName());
            turns++;
            System.out.println("__________________________________________________________________________________________________________");
            return true;
        }else if (faintHandler()==2){
            //enemy fainted
            if (!allFainted(enemy)){
                if (enemy.getLead().isFainted()){
                    boolean bool = false;
                    int x = 0;
                    while (!bool){

                        if (enemy.getSummons().get(x).isFainted()){
                            //System.out.println("Failed to switch to summon on index " + x);
                            x = rand.nextInt(4);
                        } else {
                            enemy.setLead(enemy.getSummons().get(x));
                            bool = true;
                        }
                    }
                }
            }

            System.out.printf("\n%s fainted!\n\n", enemy.getLead().getName());
            turns++;
            System.out.println("__________________________________________________________________________________________________________");
            return true;
        }
        return false;
    }
    /*
    weatherModifier - does the damage done by the move in conjunction with the weather
    @moveA - the move being used
    @a- the user
    @b- the nonuser
    @return - void
     */
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
    /*
    faintHandler - handles how fainting is done
    @param - none
    @return - whether enemy is fainted, you are fainted, or no one is
     */
private int faintHandler(){
    int faintStatus = 0;
    if (you.getLead().getHp() <=0){
        you.getLead().setHp(0);
        you.getLead().setFainted(true);
        faintStatus = 1;
}else if (enemy.getLead().getHp() <= 0){
        enemy.getLead().setHp(0);
        enemy.getLead().setFainted(true);
        faintStatus = 2;
    }
return faintStatus;
}

}
