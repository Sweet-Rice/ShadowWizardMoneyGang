

public class BattleState implements GameState{
    public TurnHandler turnHandler;

    private Game game;
    private final Wizards you;
    private final Wizards enemy;
    private int menu = 0;


    public BattleState(Game game, Wizards you, Wizards enemy) {
    this.you = you;
    this.enemy = enemy;
    turnHandler = new TurnHandler(game, you, enemy);
    this.game = game;
}

    public void enterState() {
        TurnHandler turnHandler = new TurnHandler(game, you, enemy);

    }

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
                case "1"-> x = 1;
                case "2"-> x = 2;
                case "3"-> x = 3;
                case "4"-> x = 4;
                case "Q","q" -> menu = 0;
                default -> System.out.println("Invalid input");
            }
            if (x<= you.lead.moves.size()){
                switch (x){
                    case 1 -> {
                        turnHandler.setaMove(you.lead.moves.getFirst());
                        if (turnHandler.youmoved()) {
                            turnHandler.mainTurn();
                            menu = 0;
                        }
                    }
                    case 2 -> {
                        turnHandler.setaMove(you.lead.moves.get(1));
                        if (turnHandler.youmoved()) {
                            turnHandler.mainTurn();
                            menu = 0;
                        }
                    }
                    case 3 -> {
                        turnHandler.setaMove(you.lead.moves.get(2));
                        if (turnHandler.youmoved()) {
                            turnHandler.mainTurn();
                            menu = 0;
                        }

                    }
                    case 4 -> {
                        turnHandler.setaMove(you.lead.moves.get(3));
                        if (turnHandler.youmoved()) {
                            turnHandler.mainTurn();
                            menu = 0;
                        }
                    }
                }
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

    private void switcher(int x) {
        if (x <= you.summons.size()){
            switch (x) {
                case 1-> {
                    if (!you.summons.getFirst().isFainted()) {
                        System.out.printf("Come back, %s! Go, %s!\n", you.lead.getName(), you.summons.getFirst().getName());
                        you.setLead(you.summons.getFirst());
                        turnHandler.mainTurn();
                        menu = 0;
                    } else {
                        System.out.println(you.summons.getFirst().getName() + "is fainted!");
                    }
                }
                case 2-> {
                    if (!you.summons.get(1).isFainted()) {
                        System.out.printf("Come back, %s! Go, %s!\n", you.lead.getName(), you.summons.get(1).getName());
                        you.setLead(you.summons.get(1));
                        turnHandler.mainTurn();
                        menu = 0;
                    } else {
                        System.out.println(you.summons.get(1).getName() + "is fainted!");
                    }
                }
                case 3-> {
                    if (!you.summons.get(2).isFainted()) {
                        System.out.printf("Come back, %s! Go, %s!\n", you.lead.getName(), you.summons.get(2).getName());
                        you.setLead(you.summons.get(2));
                        turnHandler.mainTurn();
                        menu = 0;
                    } else {
                        System.out.println(you.summons.get(2).getName() + "is fainted!");
                    }
                }
                case 4-> {
                    if (!you.summons.get(3).isFainted()) {
                        System.out.printf("Come back, %s! Go, %s!\n", you.lead.getName(), you.summons.get(3).getName());
                        you.setLead(you.summons.get(3));
                        turnHandler.mainTurn();
                        menu = 0;
                    } else {
                        System.out.println(you.summons.get(3).getName() + " is fainted!");
                    }

                }
                case -1 -> menu = 0; //unreachable with the forced switch menu


                default -> System.out.println("Invalid input");
            }
        } else {
            System.out.println("Invalid input");
        }
    }

    public void update() {

        if (turnHandler.allFainted(you)){
            System.out.println("All your summons have fainted! You blacked out!");
            System.exit(0);
        }
        if (turnHandler.allFainted(enemy)){
            System.out.printf("All %s's summons have fainted! You won!\n %s \n",enemy.getName(),winMessage(enemy.getName()));
            Driver.wins++;
            //save wins

            game.setState(new OutsideBattleState(game, you));
        }

        if (you.lead.isFainted()){
         menu = 3;
        }
    }
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
    public void exitState() {
        //if player loses, lose message
        // if player wins, win message and edit win var in driver
    }

    public void showMenu() {
        switch(menu){
            case 0 -> {
                showStats();
                System.out.println("What would you like to do? Press q to return.");
                System.out.print("1. Fight \t\t 2. Summons \n3. Bag \t\t\t 4. Run\n");
                System.out.print("Enter your choice: ");
            }
            case 1 -> {
                showStats();
                System.out.println("What would you like to do? Press q to return.");
                System.out.print(you.lead.MovesToString());
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
public void showStats(){
        if (!enemy.lead.isFainted()){
            System.out.print("\t\t\t\t\t\t\t\t\t\t   ");
            for (int i = 0; i<enemy.lead.getName().length()+12; i++){
                System.out.print("_");
            }
            System.out.print("\n");
            System.out.printf("\t\t\t\t\t\t\t\t\t\t  | %s %d/%d hp  |\n", enemy.lead.getName(),enemy.lead.getHp(),enemy.lead.getMaxHp());
            System.out.print("\t\t\t\t\t\t\t\t\t\t   ");
            for (int i = 0; i<enemy.lead.getName().length()+12; i++){
                System.out.print("_");
            }

        }
        if (!you.lead.isFainted()){
            System.out.print("\n     ");
            for (int i = 0; i<you.lead.getName().length()+12; i++){
                System.out.print("_");
            }
            System.out.print("\n");
            System.out.printf("\t| %s %d/%d hp  |\n",you.lead.getName(),you.lead.getHp(),you.lead.getMaxHp());
            System.out.print("     ");
            for (int i = 0; i<you.lead.getName().length()+12; i++){
                System.out.print("_");
            }System.out.print("\n\n");

        }



}

}
