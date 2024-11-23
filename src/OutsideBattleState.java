public class OutsideBattleState implements GameState {
    private final Wizards you;
    private final Game game;
    private final Wizards enemy;

    /*
    OutsideBattleState - constructor
    @game - the game class for usage of its methods
    @you - the you object of the wizards class to deal with the player

     */
    public OutsideBattleState(Game game, Wizards you) {
        this.game = game;
        this.you = you;
        this.enemy = Driver.getEnemy();
    }

    //explained in GameState
    public void enterState() {

        System.out.printf("You have %d wins, so you can fight Gym Leader %s.\n ", Driver.getWins(), enemy.getName());

    }
    //explained in GameState
    public void handleInput(String input) {
        switch (input) {
            case "1"-> /* Fight */ //if player has >0 summons,
                //else print saying you have no summons
                    game.setState(new BattleState( game, you, enemy));/* Fight */
            case "2"-> /* Summons */ game.setState(new SummonState(game, you));
            default -> System.out.println("Invalid input");
        }
        
    }
    //explained in gameState
    public void update() {
        System.out.println("erm");
    }
    //explained in gameState
    public void showMenu() {
        System.out.println("__________________________________________________________________________________________________________");
        System.out.printf("\nYour current party consists of:\n %s\nIf you fight with no summons, you will automatically lose.\n\n", you.getSummons().toString());
        System.out.println("What would you like to do?");
        System.out.printf("\n1. Fight %s\t\t 2. Edit Summons\n\n", enemy.getName());
        System.out.print("Enter your choice: ");

    }

}

