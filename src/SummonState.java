public class SummonState implements GameState {
    private Wizards you;
    private Game game;
    private int delete = 0;

    public SummonState(Game game, Wizards you) {
        this.game = game;
        this.you = you;
    }


    public void enterState() {
    System.out.println("Welcome to the SummonMenu, where you can add or delete summons in your party. Press d to toggle delete mode or q to quit.");
    System.out.printf("Your current party: %s ", you);

    }

    @Override
    public void handleInput(String input) {

        switch (delete){
            case 0->{
                int x = -1;
                switch (input) {

                    case "d"-> delete = 1;
                    case "q"-> game.setState(new OutsideBattleState(game, you));
                    default ->{
                        if (isInteger(input)) {
                            x = Integer.parseInt(input)-1;
                            if (x<Driver.AllSummons.size()){
                                if(you.getSummons().contains(Driver.AllSummons.get(x))){
                                    System.out.println("You already have this summon.");
                                }else{
                                    if (you.getSummons().size()<4){
                                        you.addSummons(Driver.AllSummons.get(x));
                                        System.out.println("__________________________________________________________________________________________________________");
                                        System.out.printf("\n%s added successfully!\n\n", Driver.AllSummons.get(x).getName());
                                    }
                                    else {
                                        System.out.println("You already have 4 summons, the max allowed.");
                                    }
                                }

                            }
                            else {
                                System.out.println("Invalid input");
                            }
                        } else {
                            System.out.println("Invalid input");
                        }
                    }
                }

            }
            case 1->{
                int x = -1;
                switch (input) {

                    case "d"-> delete = 0;
                    case "q"-> game.setState(new OutsideBattleState(game, you));
                    default ->{
                        if (isInteger(input)) {
                            x = Integer.parseInt(input);
                            if (x<you.getSummons().size()){
                                if (!Driver.AllSummons.isEmpty()){
                                    you.removeSummons(Driver.AllSummons.get(x));
                                }
                                else {
                                    System.out.println("You have no summons!");
                                }
                            }
                            else {
                                System.out.println("Invalid input");
                            }
                        } else {
                            System.out.println("Invalid input");
                        }
                    }
                }
            }

        }

    }

    @Override
    public void update() {

    }

    @Override
    public void showMenu() {
        System.out.println("__________________________________________________________________________________________________________");
        switch (delete){
            case 0 ->{
                System.out.println("\nYour party:\n");
                printParty();
                System.out.println("\n__________________________________________________________________________________________________________");

                System.out.println("Select a numbered summon to add to your party, up to four. Type d to toggle delete mode or q to quit.");
                System.out.print(Driver.getAllSummons());
            }
            case 1 ->{
                System.out.println("Select a numbered summon to delete from your party. Press d to toggle delete mode or q to quit.");
                printParty();
            }
        }
    }

    private void printParty() {
        String s = "";
        for (int i = 0; i<you.getSummons().size(); i++){
            s+= (i+1) + ". " + you.getSummons().get(i).toString() + "\n";
            s+= you.getSummons().get(i).MovesToString() + "\n";
        }
        System.out.print(s);
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
}

