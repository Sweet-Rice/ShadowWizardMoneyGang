

public class BattleState implements GameState{
    private final Game game;
    private final Wizards you;
    private final Wizards enemy;
    private int menu = 0;



    public BattleState(Game game, Wizards you, Wizards enemy) {
    this.game = game;
    this.you = you;
    this.enemy = enemy;
}

    public void enterState() {
        TurnHandler turnHandler = new TurnHandler(you, enemy);
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
                        TurnHandler.setaMove(you.lead.moves.getFirst());
                        if (TurnHandler.youmoved()) {
                            TurnHandler.mainTurn();
                            menu = 0;
                        }
                    }
                    case 2 -> {
                        TurnHandler.setaMove(you.lead.moves.get(1));
                        if (TurnHandler.youmoved()) {
                            TurnHandler.mainTurn();
                            menu = 0;
                        }
                    }
                    case 3 -> {
                        TurnHandler.setaMove(you.lead.moves.get(2));
                        if (TurnHandler.youmoved()) {
                            TurnHandler.mainTurn();
                            menu = 0;
                        }

                    }
                    case 4 -> {
                        TurnHandler.setaMove(you.lead.moves.get(3));
                        if (TurnHandler.youmoved()) {
                            TurnHandler.mainTurn();
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
            if (x <= you.summons.size()){
                switch (x) {
                    case 1-> {
                        if (!you.summons.getFirst().isFainted()) {
                            System.out.printf("Come back, %s! Go, %s!\n", you.lead.getName(), you.summons.getFirst().getName());
                            you.setLead(you.summons.getFirst());
                            if (TurnHandler.youmoved()) {
                                TurnHandler.mainTurn();
                                menu = 0;
                            }
                        } else {
                            System.out.println(you.summons.getFirst().getName() + "is fainted!");
                        }
                    }
                    case 2-> {
                        if (!you.summons.get(1).isFainted()) {
                            System.out.printf("Come back, %s! Go, %s!\n", you.lead.getName(), you.summons.get(1).getName());
                            you.setLead(you.summons.get(1));
                            if (TurnHandler.youmoved()) {
                                TurnHandler.mainTurn();
                                menu = 0;
                            }
                        } else {
                            System.out.println(you.summons.get(1).getName() + "is fainted!");
                        }
                    }
                    case 3-> {
                        if (!you.summons.get(2).isFainted()) {
                            System.out.printf("Come back, %s! Go, %s!\n", you.lead.getName(), you.summons.get(2).getName());
                            you.setLead(you.summons.get(2));
                            if (TurnHandler.youmoved()) {
                                TurnHandler.mainTurn();
                                menu = 0;

                            }
                        } else {
                            System.out.println(you.summons.get(2).getName() + "is fainted!");
                        }
                    }
                    case 4-> {
                        if (!you.summons.get(3).isFainted()) {
                            System.out.printf("Come back, %s! Go, %s!\n", you.lead.getName(), you.summons.get(3).getName());
                            you.setLead(you.summons.get(3));
                            if (TurnHandler.youmoved()) {
                                TurnHandler.mainTurn();
                                menu = 0;
                            }
                        } else {
                            System.out.println(you.summons.get(3).getName() + "is fainted!");
                        }

                    }
                    case -1 -> menu = 0;


                    default -> System.out.println("Invalid input");
                }
            } else {
                System.out.println("Invalid input");
            }

        }
    }

    }

    public void update() {

    }

    public void exitState() {
        //if player loses, lose message
        // if player wins, win message and edit win var in driver
    }

    public void showMenu() {
        switch(menu){
            case 0 -> {
                System.out.println("What would you like to do? Press q to return.");
                System.out.print("1. Fight \t\t 2. Summons \n3. Bag \t\t\t 4. Run\n");
                System.out.print("Enter your choice: ");
            }
            case 1 -> {
                System.out.println("What would you like to do? Press q to return.");
                System.out.print(you.lead.MovesToString());
                System.out.print("Enter your choice: ");
            }
            case 2 -> {
                System.out.println("What Summon would you like to swap to?");

                System.out.print(you.SummonsToString());
                System.out.print("Enter your choice: ");
            }
        }


    }


}
