public class OutsideBattleState implements GameState {
    private Wizards you;
    private Game game;
    private Wizards enemy;

    public OutsideBattleState(Game game, Wizards you) {
        this.game = game;
        this.you = you;
        this.enemy = Driver.getEnemy();
    }

    public void enterState() {

        System.out.printf("You have %d wins, so you can fight %s ", Driver.wins, enemy.toString());

    }
    public void handleInput(String input) {
        switch (input) {
            case "1"-> /* Fight */ {
                //if player has >0 summons,
                game.setState(new BattleState(game, you, enemy));/* Fight */
                //else print saying you have no summons
            }
            case "2"-> /* Summons */ game.setState(new SummonMenu(game, you));
            default -> System.out.println("Invalid input");
        }
        
    }
    public void update() {
        System.out.println("erm");
    }
    public void exitState() {
        System.out.println("exiting");
    }

    @Override
    public void showMenu() {
        System.out.println("What would you like to do?");
        System.out.print("1. Fight \t\t 2. Edit Summons\n");
        System.out.print("Enter your choice: ");
    }
}
