public class OutsideBattleState implements GameState {
    private final Wizards you;
    private final Game game;
    private final Wizards enemy;

    public OutsideBattleState(Game game, Wizards you) {
        this.game = game;
        this.you = you;
        this.enemy = Driver.getEnemy();
    }

    public void enterState() {

        System.out.printf("You have %d wins, so you can fight Gym Leader %s. ", Driver.wins, enemy.getName());

    }
    public void handleInput(String input) {
        switch (input) {
            case "1"-> /* Fight */ //if player has >0 summons,
                //else print saying you have no summons
                    game.setState(new BattleState( game, you, enemy));/* Fight */
            case "2"-> /* Summons */ game.setState(new SummonMenu(game, you));
            default -> System.out.println("Invalid input");
        }
        
    }
    public void update() {
        System.out.println("erm");
    }
    public void exitState() {

    }

    @Override
    public void showMenu() {
        System.out.println("What would you like to do?");
        System.out.printf("\n1. Fight %s\t\t 2. Edit Summons\n\n", enemy.getName());
        System.out.print("Enter your choice: ");
    }
}
