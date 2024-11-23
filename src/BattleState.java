
import java.util.Random;
public class BattleState implements GameState{


    public TurnHandler turnHandler;
    private final Game game;
    private final Wizards you;
    private final Wizards enemy;
    private int menu = 0;
    private Random rand = new Random();

    /*
    BattleState - Constructor for the BattleState
    @game - refers to Game class, to utilize setState

     */
    public BattleState(Game game, Wizards you, Wizards enemy) {
    this.you = you;
    this.enemy = enemy;
    turnHandler = new TurnHandler(game, you, enemy);
    this.game = game;
}

    //explained in GameState.java
    public void enterState() {
        TurnHandler turnHandler = new TurnHandler(game, you, enemy);

    }

    //explained in GameState.java
    public void handleInput(String input) {
    switch (menu){
        case 0 -> {
            switch (input) {
                case "1"-> /* Fight */ menu = 1;/* Fight */
                case "2"-> /* Summons */ menu = 2;
                case "3"-> /* Bag */ System.out.println("You can't use that here!");
                case "4"-> /* Run */ System.out.println("No! You can't run from a wizard battle!");
                default -> System.out.println("Invalid input");
            }
        }
        case 1 -> {
            int x = -1;
            switch (input) {
                case "1"-> x = 0;
                case "2"-> x = 1;
                case "3"-> x = 2;
                case "4"-> x = 3;
                case "Q","q" -> menu = 0;
                default -> System.out.println("Invalid input");
            }
            if (x< you.getLead().getMoves().size()){
                if (you.getLead().getMoves().get(x).getPp()!=0){

                    turnHandler.setaMove(you.getLead().getMoves().get(x));
                    if (turnHandler.youmoved()) {
                        turnHandler.mainTurn();
                        menu = 0;
                    }

                }
                else {
                    System.out.printf("%s does not have PP for that move. It panicked and did nothing!\n", you.getLead().getName());
                    turnHandler.mainTurn();
                }
            } else {
                System.out.println("Invalid input");
            }
        }
        case 2 -> {
            int x = -1;
            switch (input) {
                case "1" -> x = 1;
                case "2" -> x = 2;
                case "3" -> x = 3;
                case "4" -> x = 4;
                case "q", "Q" -> menu = 0;
                default -> System.out.println("Invalid input");
            }
            switcher(x);

        }
        case 3 -> {
            int x = -1;
        switch (input) {
            case "1" -> x = 1;
            case "2" -> x = 2;
            case "3" -> x = 3;
            case "4" -> x = 4;

            default -> System.out.println("Invalid input");
        }
            switcher(x);

        }
    }

    }

    //explained in GameState.java
    public void update() {

        if (turnHandler.allFainted(you)){
            System.out.println("You have no usable summons! You blacked out!");
            System.exit(0);
        }
        if (turnHandler.allFainted(enemy)){
            System.out.printf("%s has no usable summons! You won!\n %s \n",enemy.getName(),winMessage(enemy.getName()));
            Driver.win();
            //save wins
            //heals all party members
            for (int i = 0; i < you.getSummons().size(); i++) {
                you.getSummons().get(i).reset();
            }
            game.setState(new OutsideBattleState(game, you));
        }

        if (you.getLead().isFainted()){
         menu = 3;
        }
    }

    //Explained in GameStatejava
    public void showMenu() {
        System.out.println("__________________________________________________________________________________________________________");

        switch(menu){
            case 0 -> {
                showStats();
                System.out.println("What would you like to do?");
                System.out.print("1. Fight \t\t 2. Summons \n3. Bag \t\t\t 4. Run\n");
                System.out.print("Enter your choice: ");
            }
            case 1 -> {
                showStats();
                System.out.println("What would you like to do? Press q to return.");
                System.out.print(you.getLead().MovesToString());
                System.out.print("Enter your choice: ");
            }
            case 2 -> {
                showStats();
                System.out.println("What Summon would you like to swap to?");

                System.out.print(you.SummonsToString());
                System.out.print("Enter your choice: ");
            }
            case 3 -> {

                System.out.println("Your summon can no longer fight. What Summon would you like to swap to?");
                System.out.print(you.SummonsToString());
                System.out.print("Enter your choice: ");
            }
        }


    }

    /*
    winMessage - creates a win message
    @wiz - name of wizard that gets defeated
    @return - returns the win message as a string
     */
    private String winMessage(String wiz) {
        String s = "shouldn't be able to read this";
        String zero = "Zero: Drats!";
        String one = "One: ";
        String two = "Two: ";
        String three = "Three: ";
        String four = "Four: ";
        String five = "Five: ";
        String six = "Six: ";
        String seven = "Seven: ";
        switch (wiz){
            case "Zero" -> s = zero;
            case "One" -> s = one;
            case "Two" -> s = two;
            case "Three" -> s = three;
            case "Four" -> s = four;
            case "Five" -> s = five;
            case "Six" -> s = six;
            case "Seven" -> s = seven;
        }
        return s + "\n";
    }

    /*
    switcher - provides the summon switching functionality based on menu variable
    @x - menu variable. voluntary switch or switch after fainted
    @return - void
     */
    private void switcher(int x) {
        if (x <= you.getSummons().size()){
            switch (x) {
                case 1-> {
                    if (!you.getSummons().getFirst().isFainted()) {
                        System.out.printf("Come back, %s! Go, %s!\n", you.getLead().getName(), you.getSummons().getFirst().getName());
                        you.setLead(you.getSummons().getFirst());
                        turnHandler.mainTurn();
                        menu = 0;
                    } else {
                        System.out.println(you.getSummons().getFirst().getName() + "is fainted!");
                    }
                }
                case 2-> {
                    if (!you.getSummons().get(1).isFainted()) {
                        System.out.printf("Come back, %s! Go, %s!\n", you.getLead().getName(), you.getSummons().get(1).getName());
                        you.setLead(you.getSummons().get(1));
                        turnHandler.mainTurn();
                        menu = 0;
                    } else {
                        System.out.println(you.getSummons().get(1).getName() + "is fainted!");
                    }
                }
                case 3-> {
                    if (!you.getSummons().get(2).isFainted()) {
                        System.out.printf("Come back, %s! Go, %s!\n", you.getLead().getName(), you.getSummons().get(2).getName());
                        you.setLead(you.getSummons().get(2));
                        turnHandler.mainTurn();
                        menu = 0;
                    } else {
                        System.out.println(you.getSummons().get(2).getName() + "is fainted!");
                    }
                }
                case 4-> {
                    if (!you.getSummons().get(3).isFainted()) {
                        System.out.printf("Come back, %s! Go, %s!\n", you.getLead().getName(), you.getSummons().get(3).getName());
                        you.setLead(you.getSummons().get(3));
                        turnHandler.mainTurn();
                        menu = 0;
                    } else {
                        System.out.println(you.getSummons().get(3).getName() + " is fainted!");
                    }

                }
                case -1 -> menu = 0; //unreachable with the forced switch menu


                default -> System.out.println("Invalid input");
            }
        } else {
            System.out.println("Invalid input");
        }
    }

    /*
    showStats - shows the stats that concern the player's and the enemy's lead summons
    @param - none
    @return - void
     */
    public void showStats(){
        if (!enemy.getLead().isFainted()){
            System.out.print("\n");
            System.out.printf("\t\t\t\t\t\t\t\t\t\t  | %s |%s|%s| %d/%d hp  |\n", enemy.getLead().getName(), enemy.getLead().getType1(), enemy.getLead().getType2(), enemy.getLead().getHp(),enemy.getLead().getMaxHp());
            System.out.print("\t\t\t\t\t\t\t\t\t\t   ");


        }
        if (!you.getLead().isFainted()){
            System.out.print("\n     ");
            System.out.print("\n");
            System.out.printf("\t| %s |%s|%s| %d/%d hp  |\n",you.getLead().getName(), you.getLead().getType1(), you.getLead().getType2(),you.getLead().getHp(),you.getLead().getMaxHp());
            System.out.print("     ");


        }
        System.out.println("__________________________________________________________________________________________________________");

    }

}
