import java.util.Scanner;

public class Game {
    private GameState currentState;
    private Scanner scanner = new Scanner(System.in);



    /*
    setState - sets the current(if any) gameState to the one to be used
    @state - the GameState to be switched to
    @return - void
     */
    public void setState(GameState state) {
        currentState = state;
        currentState.enterState();
    }

    /*
    play - a continuous loop of functions until the game ends
    @param - none
    @return - void
     */
    public void play(){
        boolean Sent = false;
        while (!Sent ) {
            if (currentState!=null){
                currentState.showMenu();
                String input = scanner.nextLine().trim();
                currentState.handleInput(input);
                currentState.update();
            }
        }
    }
}
