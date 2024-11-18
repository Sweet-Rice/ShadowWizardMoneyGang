public class DamageCalc {


    public static double getAdv(Summons summon, Moves move) {

        // mfw idk how to use hash tables


        double mult1 = -1.0;
        double mult2 = -1.0;

        /*
        quick copypaste for later use:
        switch (move.type) {
                    case  -> mult1 = 0.5;
                    case  -> mult1 = 2.0;
                    default -> mult1 = 1.0;
         */
        mult1 = getMult(summon, move, mult1);
        mult2 = getMult(summon, move, mult2);
        return (mult1 * mult2);
    }

    private static double getMult(Summons summon, Moves move, double mult1) {
        switch (summon.type2) {
            case Bug -> {
                switch (move.type) {
                    case Grass, Fighting, Ground -> mult1 = 0.5;
                    case Fire, Poison, Flying, Rock -> mult1 = 2.0;
                    default -> mult1 = 1.0;
                }
            }
            case Dragon -> {
                switch (move.type) {
                    case Fire, Water, Electric, Grass -> mult1 = 0.5;
                    case Ice, Dragon -> mult1 = 2.0;
                    default -> mult1 = 1.0;
                }
            }
            case Electric -> {
                switch (move.type) {
                    case Electric, Flying -> mult1 = 0.5;
                    case Ground -> mult1 = 2.0;
                    default -> mult1 = 1.0;
                }
            }
            case Fighting -> {
                switch (move.type) {
                    case Bug, Rock -> mult1 = 0.5;
                    case Flying, Psychic -> mult1 = 2.0;
                    default -> mult1 = 1.0;
                }
            }
            case Fire -> {
                switch (move.type) {
                    case Fire, Grass, Bug -> mult1 = 0.5;
                    case Water, Ground, Rock -> mult1 = 2.0;
                    default -> mult1 = 1.0;
                }
            }
            case Flying -> {
                switch (move.type) {
                    case Grass, Fighting, Bug -> mult1 = 0.5;
                    case Electric, Ice, Rock -> mult1 = 2.0;
                    case Ground -> mult1 = 0.0;
                    default -> mult1 = 1.0;
                }
            }
            case Ghost -> {
                switch (move.type) {
                    case Poison, Bug -> mult1 = 0.5;
                    case Ghost -> mult1 = 2.0;
                    case Normal, Fighting -> mult1 = 0.0;
                    default -> mult1 = 1.0;
                }
            }
            case Grass -> {
                switch (move.type) {
                    case Water, Electric, Grass, Ground -> mult1 = 0.5;
                    case Fire, Ice, Poison, Flying, Bug -> mult1 = 2.0;
                    default -> mult1 = 1.0;
                }
            }
            case Ground -> {
                switch (move.type) {
                    case Poison, Rock -> mult1 = 0.5;
                    case Water, Grass, Ice -> mult1 = 2.0;
                    case Electric -> mult1 = 0.0;
                    default -> mult1 = 1.0;
                }
            }
            case Ice -> {
                switch (move.type) {
                    case Ice -> mult1 = 0.5;
                    case Fire, Fighting, Rock -> mult1 = 2.0;
                    default -> mult1 = 1.0;
                }
            }
            case Normal -> {
                switch (move.type) {
                    case Ghost -> mult1 = 0.0;
                    case Fighting -> mult1 = 2.0;
                    default -> mult1 = 1.0;
                }
            }
            case Poison -> {
                switch (move.type) {
                    case Grass, Fighting, Poison -> mult1 = 0.5;
                    case Ground, Psychic, Bug -> mult1 = 2.0;
                    default -> mult1 = 1.0;
                }
            }
            case Psychic -> {
                switch (move.type) {
                    case Fighting, Psychic -> mult1 = 0.5;
                    case Bug -> mult1 = 2.0;
                    case Ghost -> mult1 = 0.0;
                    default -> mult1 = 1.0;
                }
            }
            case Rock -> {
                switch (move.type) {
                    case Normal, Fire, Poison, Flying -> mult1 = 0.5;
                    case Water, Grass, Fighting, Ground -> mult1 = 2.0;
                    default -> mult1 = 1.0;
                }
            }
            case Water -> {
                switch (move.type) {
                    case Fire, Water, Ice -> mult1 = 0.5;
                    case Electric, Grass -> mult1 = 2.0;
                    default -> mult1 = 1.0;
                }
            }
            case Null -> {
                mult1 = 1.0;
            }
        }
        return mult1;
    }
}
