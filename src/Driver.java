import java.util.ArrayList;
public class Driver {
     public static int wins;
    public static ArrayList<Wizards> GymLeaders = new ArrayList<>();

    private static void addWizards(Wizards wizard){
        GymLeaders.add(wizard);
    }
    public static void init(){
    System.out.println("Welcome to the wonderful world of Summons, wizard!");
    }

    public static Wizards getEnemy(){
        Wizards wiz;
        switch (wins) {
            case 1 -> wiz = GymLeaders.get(1);
            case 2 -> wiz = GymLeaders.get(2);
            case 3 -> wiz = GymLeaders.get(3);
            case 4 -> wiz = GymLeaders.get(4);
            case 5 -> wiz = GymLeaders.get(5);
            case 6 -> wiz = GymLeaders.get(6);
            case 7 -> wiz = GymLeaders.get(7);
            case 0 -> wiz = GymLeaders.get(0);
            default -> {
                System.out.print("CONGRATULATIONS! You managed to defeat every Gym Leader!\nYou have successfully been initiated into the \"Shadow Wizard Money Gang!!! \"");
                System.exit(1);
                wiz = GymLeaders.getFirst();
            }
        }
        return wiz;
    }


    public static void main(String[] args) {
        init();
        Game game = new Game();
        //Moves init
        // sample init Moves tackle = new Moves("tackle", DamageCalc.Type.Normal, Moves.Dmg.Atk, 35,"The user hurls itself at the target.") ;

        Moves tackle = new Moves("tackle", DamageCalc.Type.Normal, Moves.Dmg.Atk, 35,30, Moves.Status.Null, Moves.Who.Opposite,"The user hurls itself at the target.") ;
        Moves gust = new Moves("gust", DamageCalc.Type.Fly, Moves.Dmg.Spatk, 50, 15, Moves.Status.Rain, Moves.Who.Both, "test");
        Moves fightingTest = new Moves("fightingTest", DamageCalc.Type.Fighting, Moves.Dmg.Atk, 50, 10, Moves.Status.Burn, Moves.Who.Drain, "hello");
        Moves sleeper = new Moves("sleeper", DamageCalc.Type.Normal, Moves.Dmg.Spatk, 0, 10, Moves.Status.Slp, Moves.Who.Opposite, "go to sleep");
        Moves paralyze = new Moves ("paralyzer", DamageCalc.Type.Normal, Moves.Dmg.Spatk, 0, 10, Moves.Status.Par, Moves.Who.Opposite, "turn enemies into my ranked teammates");
        //summmon init
        //sample init
        Summons myratatta = new Summons("myratatta", DamageCalc.Type.Normal, DamageCalc.Type.Null, 30, 30, 30, 30, 40000000, 31);
        myratatta.addMove(tackle); myratatta.addMove(paralyze); myratatta.addMove(fightingTest); myratatta.addMove(sleeper);

        Summons enemyratatta = new Summons("enemyratatta", DamageCalc.Type.Normal, DamageCalc.Type.Null, 30, 30, 30, 30, 40, 30);
        enemyratatta.addMove(tackle); enemyratatta.addMove(tackle);

        Summons pidgey = new Summons("pidgey", DamageCalc.Type.Fly, DamageCalc.Type.Normal, 30, 30, 100, 30, 30, 30);
        pidgey.addMove(tackle); pidgey.addMove(gust);

        //Wizard init
        Wizards You = new Wizards("You");
        You.addSummons(myratatta);

        Wizards Zero = new Wizards("Zero");
        Zero.addSummons(enemyratatta);

        Wizards One = new Wizards("One");
        One.addSummons(pidgey);

        Wizards Two = new Wizards("Two");
        Wizards Three = new Wizards("Three");
        Wizards Four = new Wizards("Four");
        Wizards Five = new Wizards("Five");
        Wizards Six = new Wizards("Six");
        Wizards Seven = new Wizards("Seven");


        addWizards(Zero); addWizards(One); addWizards(Two); addWizards(Three); addWizards(Four);
        addWizards(Five); addWizards(Six); addWizards(Seven);




        GameState OutsideBattleState = new OutsideBattleState(game, You);
        game.setState(OutsideBattleState);

        game.play();

    }
}
