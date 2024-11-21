import java.util.Scanner;

public class Game {
    private GameState currentState;
    private Scanner scanner = new Scanner(System.in);
    //handles gamestates and their transitions


    public GameState getCurrentState() {
        return currentState;
    }

    public void setState(GameState state) {
        if (currentState != null) {
            currentState.exitState();
        }
        currentState = state;
        currentState.enterState();
    }

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
