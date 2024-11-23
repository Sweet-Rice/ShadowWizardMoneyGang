public interface GameState {
    void enterState();
    void handleInput(String input);
    void update();
    void showMenu();

}
