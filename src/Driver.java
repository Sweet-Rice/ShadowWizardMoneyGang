import java.util.ArrayList;
public class Driver {

    public static ArrayList<Wizards> GymLeaders = new ArrayList<>();
    public static ArrayList<Summons> AllSummons = new ArrayList<>();
    private static int wins;


    public static void win(){
    wins++;
    }
    public static int getWins(){
        return wins;
    }

    public static String getAllSummons(){
        String output = "";
        for (int i = 0; i < AllSummons.size(); i++){
            output += (i+1) + ". " + AllSummons.get(i).toString() + "\n";
            output += AllSummons.get(i).MovesToString() + "\n";
        }
        return output;
    }

    private static void addWizards(Wizards wizard){
        GymLeaders.add(wizard);
    }
    private static void init(){
        System.out.println("__________________________________________________________________________________________________________");
        System.out.print("\n");
        System.out.println("Welcome to the wonderful world of Summons, wizard!");
        System.out.println("In this world, you need to prove your worth to the Shadow Wizard Money Gang. You must fight 2 \n" +
                "Of their best wizards in a fight of summons. When you banish these summons, the wizards really like to call it fainting\n" +
                "because of some video game they played long ago, so just accept that. \n " +
                "\nAnyways, these fights will be turn based, with the speed of your respective summons determining who goes first.");
        System.out.print("\n");
    }

    public static Wizards getEnemy(){
        Wizards wiz;
        switch (wins) {
            case 1 -> wiz = GymLeaders.get(1);
            //case 2 -> wiz = GymLeaders.get(2);
            /*
            case 3 -> wiz = GymLeaders.get(3);
            case 4 -> wiz = GymLeaders.get(4);
            case 5 -> wiz = GymLeaders.get(5);
            case 6 -> wiz = GymLeaders.get(6);
            case 7 -> wiz = GymLeaders.get(7);

             */
            case 0 -> wiz = GymLeaders.get(0);


            default -> {
                System.out.print("CONGRATULATIONS! You managed to defeat every Gym Leader!\nYou have successfully been initiated into the \"Shadow Wizard Money Gang!!! \"");
                System.exit(1);
                wiz = GymLeaders.getFirst();
            }
        }
        return wiz;
    }


    public static void main(String[] args) {
        init();
        Game game = new Game();
        //Moves init
        // sample init Moves tackle = new Moves("tackle", DamageCalc.Type.Normal, Moves.Dmg.Atk, 35,"The user hurls itself at the target.") ;
        /*
        Moves tackle = new Moves("tackle", DamageCalc.Type.Normal, Moves.Dmg.Atk, 35,30, Moves.Status.Null, Moves.Who.Opposite,"The user hurls itself at the target.") ;
        Moves gust = new Moves("gust", DamageCalc.Type.Fly, Moves.Dmg.Spatk, 50, 15, Moves.Status.Rain, Moves.Who.Both, "test");
        Moves fightingTest = new Moves("fightingTest", DamageCalc.Type.Fighting, Moves.Dmg.Atk, 50, 10, Moves.Status.Burn, Moves.Who.Drain, "hello");
        Moves sleeper = new Moves("sleeper", DamageCalc.Type.Normal, Moves.Dmg.Spatk, 0, 10, Moves.Status.Slp, Moves.Who.Opposite, "go to sleep");
        Moves paralyze = new Moves ("paralyzer", DamageCalc.Type.Normal, Moves.Dmg.Spatk, 0, 10, Moves.Status.Par, Moves.Who.Opposite, "turn enemies into my ranked teammates");
        */
        Moves Panic = new Moves("Panic", DamageCalc.Type.Normal, Moves.Dmg.Atk, 0, 1, Moves.Status.Null, Moves.Who.Opposite, "Panic");
        Moves Blizzard = new Moves("Blizzard", DamageCalc.Type.Ice, Moves.Dmg.Spatk, 80, 15,Moves.Status.Null, Moves.Who.Opposite, "Summon a short lived blizzard onto the enemy.");
        Moves Shock = new Moves ("Shock", DamageCalc.Type.Electric, Moves.Dmg.Spatk, 30, 15, Moves.Status.Par, Moves.Who.Opposite, "Shock the enemy, giving them paralysis." );
        Moves WillOWisp = new Moves ("Will-O-Wisp", DamageCalc.Type.Fire, Moves.Dmg.Spatk, 0, 15, Moves.Status.Burn, Moves.Who.Opposite, "Give the enemy a burn.");
        Moves ShadowBall = new Moves ("Shadow Ball", DamageCalc.Type.Ghost, Moves.Dmg.Spatk, 80, 15, Moves.Status.Null, Moves.Who.Opposite, "Throw a ball of energy from the spirit realm.");

        Moves Venom = new Moves ("Venom", DamageCalc.Type.Poison, Moves.Dmg.Spatk, 0, 15, Moves.Status.Pois, Moves.Who.Opposite, "Poisons the target");
        Moves Surf = new Moves ("Surf", DamageCalc.Type.Water, Moves.Dmg.Spatk, 90, 15, Moves.Status.Null, Moves.Who.Opposite, "Send massive waves to the target.");
        Moves Crash = new Moves ("Crash", DamageCalc.Type.Normal, Moves.Dmg.Atk, 130, 5, Moves.Status.Null, Moves.Who.Recoil, "Hurls itself at the target, dealing recoil damage.");

        Moves Polterfight = new Moves("Polterfight", DamageCalc.Type.Ghost, Moves.Dmg.Atk, 110, 10, Moves.Status.Null, Moves.Who.Opposite, "The user's shadow attacks the target.");
        Moves IceShard = new Moves("Ice Shard", DamageCalc.Type.Ghost, Moves.Dmg.Atk, 0, 15, Moves.Status.Spd, Moves.Who.Self, "The user sheds a piece of ice from its body, letting it move faster.");
        Moves ColdPlay = new Moves("Cold Play", DamageCalc.Type.Ice, Moves.Dmg.Atk, 90, 10, Moves.Status.Null, Moves.Who.Opposite, "The user's low body temperature enhances its attack");

        Moves Earthquake = new Moves("Earthquake", DamageCalc.Type.Ground, Moves.Dmg.Atk, 100, 15, Moves.Status.Null, Moves.Who.Both, "The user summons an earthquake, hurting itself as well.");
        Moves SuperPunch = new Moves ("Super Punch", DamageCalc.Type.Fighting, Moves.Dmg.Atk, 120, 5, Moves.Status.Atk, Moves.Who.Opposite, "Hits the target so hard it inspires their strength.");

        Summons FridgeGhost = new Summons("Fridge Ghost", DamageCalc.Type.Electric, DamageCalc.Type.Ice, 65, 105, 107, 107, 50, 86);
        FridgeGhost.addMove(Blizzard); FridgeGhost.addMove(Shock); FridgeGhost.addMove(WillOWisp); FridgeGhost.addMove(ShadowBall); FridgeGhost.addMove(ShadowBall);
        Summons IcyWalrus = new Summons("Icy Walrus", DamageCalc.Type.Ice, DamageCalc.Type.Water, 80,95,90,90, 110, 65);
        IcyWalrus.addMove(Venom); IcyWalrus.addMove(Blizzard); IcyWalrus.addMove(Surf); IcyWalrus.addMove(Crash);
        Summons FrozenMaiden = new Summons("Frozen Maiden", DamageCalc.Type.Ice, DamageCalc.Type.Ghost, 80, 80, 70, 70, 70, 110);
        FrozenMaiden.addMove(Polterfight); FrozenMaiden.addMove(IceShard);FrozenMaiden.addMove(ColdPlay);FrozenMaiden.addMove(Blizzard);
        Summons WoolyMammoth = new Summons("Wooly Mammoth", DamageCalc.Type.Ice, DamageCalc.Type.Ground, 130, 70, 80, 60, 110, 80);
        WoolyMammoth.addMove(IceShard); WoolyMammoth.addMove(ColdPlay); WoolyMammoth.addMove(Earthquake); WoolyMammoth.addMove(SuperPunch);

        Moves HailMary = new Moves("Hail Mary", DamageCalc.Type.Fly, Moves.Dmg.Atk, 1000, 1, Moves.Status.Null, Moves.Who.Both, "The user sacrifices itself to faint the enemy.");
        Moves Tailwind = new Moves("Tailwind", DamageCalc.Type.Fly, Moves.Dmg.Spatk, 0, 10, Moves.Status.Spd, Moves.Who.Self, "The user flaps its wings to make a tailwind.");

        Moves Roost = new Moves("Roost", DamageCalc.Type.Fly, Moves.Dmg.Atk, 50, 10, Moves.Status.Null, Moves.Who.Self, "The user rests in its nest to heal.");
        Moves VenomShock = new Moves ("VenomShock", DamageCalc.Type.Poison, Moves.Dmg.Atk, 60, 15, Moves.Status.Pois, Moves.Who.Opposite, "The user sinks its venomous fangs into the target.");

        Moves MightyFang = new Moves ("MightyFang", DamageCalc.Type.Dragon, Moves.Dmg.Atk, 80, 15, Moves.Status.Null, Moves.Who.Opposite, "The user sinks its draconic fangs into the target.");
        Moves NorthWind = new Moves ("Northwind", DamageCalc.Type.Fly, Moves.Dmg.Atk, 110, 10, Moves.Status.Null, Moves.Who.Recoil, "The user sends itself at the target using the North Wind, but takes recoil damage.");

        Moves Sharpen = new Moves("Sharpen", DamageCalc.Type.Normal, Moves.Dmg.Atk, 0, 15, Moves.Status.Atk, Moves.Who.Self, "The user sharpens itself.");
        Moves IceFang = new Moves( "Ice Fang", DamageCalc.Type.Ice, Moves.Dmg.Atk, 75, 15, Moves.Status.Null, Moves.Who.Opposite, "The user sinks its icy fangs into the enemy.");


        Summons Bigdgeon = new Summons("Bigdgeon", DamageCalc.Type.Normal, DamageCalc.Type.Fly, 120, 50, 70, 60, 85, 100);
        Bigdgeon.addMove(HailMary); Bigdgeon.addMove(Tailwind); Bigdgeon.addMove(Crash); Bigdgeon.addMove(SuperPunch);
        Summons Pollubat = new Summons("Pollubat", DamageCalc.Type.Poison, DamageCalc.Type.Fly, 90, 70, 80, 80, 85, 130);
        Pollubat.addMove(HailMary); Pollubat.addMove(VenomShock); Pollubat.addMove(Roost); Pollubat.addMove(Roost);
        Summons Fishlord = new Summons ("Fishlord", DamageCalc.Type.Water, DamageCalc.Type.Fly, 125, 60, 79, 60, 95, 81);
        Fishlord.addMove(MightyFang); Fishlord.addMove(Tailwind); Fishlord.addMove(Earthquake); Fishlord.addMove(NorthWind);
        Summons Oxymon = new Summons ("Oxymon", DamageCalc.Type.Fly, DamageCalc.Type.Ground, 95, 45, 125, 75, 75, 95);
        Oxymon.addMove(Sharpen); Oxymon.addMove(IceFang); Oxymon.addMove(Earthquake); Oxymon.addMove(Roost);


        Summons FridgeGhost1 = new Summons("Fridge Ghost", DamageCalc.Type.Electric, DamageCalc.Type.Ice, 65, 105, 107, 107, 50, 86);
        FridgeGhost1.addMove(Blizzard); FridgeGhost1.addMove(Shock); FridgeGhost1.addMove(WillOWisp); FridgeGhost1.addMove(ShadowBall); FridgeGhost1.addMove(ShadowBall);
        Summons IcyWalrus1 = new Summons("Icy Walrus", DamageCalc.Type.Ice, DamageCalc.Type.Water, 80,95,90,90, 110, 65);
        IcyWalrus1.addMove(Venom); IcyWalrus1.addMove(Blizzard); IcyWalrus1.addMove(Surf); IcyWalrus1.addMove(Crash);
        Summons FrozenMaiden1 = new Summons("Frozen Maiden", DamageCalc.Type.Ice, DamageCalc.Type.Ghost, 80, 80, 70, 70, 70, 110);
        FrozenMaiden1.addMove(Polterfight); FrozenMaiden1.addMove(IceShard);FrozenMaiden1.addMove(ColdPlay);FrozenMaiden1.addMove(Blizzard);
        Summons WoolyMammoth1 = new Summons("Wooly Mammoth", DamageCalc.Type.Ice, DamageCalc.Type.Ground, 130, 70, 80, 60, 110, 80);
        WoolyMammoth1.addMove(IceShard); WoolyMammoth1.addMove(ColdPlay); WoolyMammoth1.addMove(Earthquake); WoolyMammoth1.addMove(SuperPunch);
        Summons Bigdgeon1 = new Summons("Bigdgeon", DamageCalc.Type.Normal, DamageCalc.Type.Fly, 120, 50, 70, 60, 85, 100);
        Bigdgeon1.addMove(HailMary); Bigdgeon1.addMove(Tailwind); Bigdgeon1.addMove(Crash); Bigdgeon1.addMove(SuperPunch);
        Summons Pollubat1 = new Summons("Pollubat", DamageCalc.Type.Poison, DamageCalc.Type.Fly, 90, 70, 80, 80, 85, 130);
        Pollubat1.addMove(HailMary); Pollubat1.addMove(VenomShock); Pollubat1.addMove(Roost); Pollubat1.addMove(Roost);
        Summons Fishlord1 = new Summons ("Fishlord", DamageCalc.Type.Water, DamageCalc.Type.Fly, 125, 60, 79, 60, 95, 81);
        Fishlord1.addMove(MightyFang); Fishlord1.addMove(Tailwind); Fishlord1.addMove(Earthquake); Fishlord1.addMove(NorthWind);
        Summons Oxymon1 = new Summons ("Oxymon", DamageCalc.Type.Fly, DamageCalc.Type.Ground, 95, 45, 125, 75, 75, 95);
        Oxymon1.addMove(Sharpen); Oxymon1.addMove(IceFang); Oxymon1.addMove(Earthquake); Oxymon1.addMove(Roost);







        Summons GodRatatta = new Summons("God Debug Ratatta", DamageCalc.Type.Normal, DamageCalc.Type.Null, 10000, 10000, 10000, 10000, 10000, 10000);
        GodRatatta.addMove(Blizzard); GodRatatta.addMove(Panic);

        AllSummons.add(FridgeGhost1);
        AllSummons.add(IcyWalrus1);
        AllSummons.add(FrozenMaiden1);
        AllSummons.add(WoolyMammoth1);

        AllSummons.add(Bigdgeon1);
        AllSummons.add(Pollubat1);
        AllSummons.add(Fishlord1);
        AllSummons.add(Oxymon1);

        AllSummons.add(GodRatatta);
        /*

         */


        //Wizard init
        Wizards You = new Wizards("You");

        Wizards Zero = new Wizards("The Ice King");
        Zero.addSummons(FridgeGhost); Zero.addSummons(IcyWalrus); Zero.addSummons(WoolyMammoth); Zero.addSummons(FrozenMaiden);

        Wizards One = new Wizards("The Sorcerer of the Winds");
        One.addSummons(Bigdgeon); One.addSummons(Pollubat); One.addSummons(Fishlord); One.addSummons(Oxymon);

        Wizards Two = new Wizards("Two");
        Wizards Three = new Wizards("Three");
        Wizards Four = new Wizards("Four");
        Wizards Five = new Wizards("Five");
        Wizards Six = new Wizards("Six");
        Wizards Seven = new Wizards("Seven");


        addWizards(Zero); addWizards(One); addWizards(Two); addWizards(Three); addWizards(Four);
        addWizards(Five); addWizards(Six); addWizards(Seven);




        GameState OutsideBattleState = new OutsideBattleState(game, You);
        game.setState(OutsideBattleState);

        game.play();

    }
}
