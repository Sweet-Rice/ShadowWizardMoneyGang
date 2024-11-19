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
        return switch (wins) {
            case 1 -> GymLeaders.get(1);
            case 2 -> GymLeaders.get(2);
            case 3 -> GymLeaders.get(3);
            case 4 -> GymLeaders.get(4);
            case 5 -> GymLeaders.get(5);
            case 6 -> GymLeaders.get(6);
            case 7 -> GymLeaders.get(7);
            default -> GymLeaders.get(0);
        };
    }


    public static void main(String[] args) {
        init();

        //Moves init
        // sample init Moves tackle = new Moves("tackle", DamageCalc.Type.Normal, Moves.Dmg.Atk, 35,"The user hurls itself at the target.") ;
        Moves tackle = new Moves("tackle", DamageCalc.Type.Normal, Moves.Dmg.Atk, 35,"The user hurls itself at the target.") ;


        //summmon init
        //sample init
        Summons ratatta = new Summons("ratatta", DamageCalc.Type.Normal, DamageCalc.Type.Null, 30, 30, 30, 30, 30, 30);
        ratatta.addMove(tackle);

        //Wizard init
        Wizards You = new Wizards("You");
        You.addSummons(ratatta);
        Wizards Zero = new Wizards("Zero");
        Wizards One = new Wizards("One");
        Wizards Two = new Wizards("Two");
        Wizards Three = new Wizards("Three");
        Wizards Four = new Wizards("Four");
        Wizards Five = new Wizards("Five");
        Wizards Six = new Wizards("Six");
        Wizards Seven = new Wizards("Seven");


        addWizards(Zero); addWizards(One); addWizards(Two); addWizards(Three); addWizards(Four);
        addWizards(Five); addWizards(Six); addWizards(Seven);


        Game game = new Game();

        GameState OutsideBattleState = new OutsideBattleState(game, You);
        game.setState(OutsideBattleState);

        game.play();

    }
}
