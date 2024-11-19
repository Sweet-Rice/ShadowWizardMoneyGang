public class SummonMenu implements GameState {
    private Wizards you;
    private Game game;
    private boolean delete = false;

    public SummonMenu(Game game, Wizards you) {
        this.game = game;
        this.you = you;
    }

    @Override
    public void enterState() {
    System.out.println("Welcome to the SummonMenu, where you can add or delete summons in your party. Press d to toggle delete mode.");
    System.out.printf("Your current party: %s ", you);
    }

    @Override
    public void handleInput(String input) {

    }

    @Override
    public void update() {

    }

    @Override
    public void exitState() {

    }

    @Override
    public void showMenu() {

    }
}
