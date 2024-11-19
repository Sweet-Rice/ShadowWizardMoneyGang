public class BattleState implements GameState{
    private Game game;
    private Wizards you;
    private Wizards enemy;
public BattleState(Game game, Wizards you, Wizards enemy) {
    this.game = game;
    this.you = you;
    this.enemy = enemy;
}
    @Override
    public void enterState() {
        
    }

    @Override
    public void handleInput(String input) {
    switch (input) {
        case "1"-> /* Fight */ game.setState(new FightMenu(game, you));/* Fight */
        case "2"-> /* Summons */ game.setState(new SummonMenu(game, you));
        case "3"-> /* Bag */ System.out.println("You can't use that here!");
        case "4"-> /* Run */ System.out.println("No! You can't run from a wizard battle!");
        default -> System.out.println("Invalid input");
    }
    }

    @Override
    public void update() {

    }

    @Override
    public void exitState() {
        //if player loses, lose message
        // if player wins, win message and edit win var in driver
    }

    @Override
    public void showMenu() {
        System.out.println("What would you like to do?");
        System.out.print("1. Fight \t\t 2. Summons \n3. Bag \t\t 4. Run\n");
        System.out.print("Enter your choice: ");

    }
}
