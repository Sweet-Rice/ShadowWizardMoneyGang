public interface GameState {
    //DISCLAIMER I dont fully understand GameState. However, I have been treating it as a class that can store
    //my other gamestates with a nice template of methods that can be used by the Game class without calling those states

    /*
    enterState - initial action as soon as gameState is switched
    @param - none
    @return - void
     */
    void enterState();

    /*
    handleInput - how the gameStates should handle their respective inputs
    @param - none
    @return - void
     */
    void handleInput(String input);

    /*
    update - what to do on each iteration of an infinite while loop (until explicitly exited)
    @param - none
    @return - void
     */
    void update();

    /*
    showMenu - menu to be displayed before the player inputs something
    @param - none
    @return - void
     */
    void showMenu();

}
