public class Moves {

    public enum Dmg { Atk, Spatk}



    private final String name;
    public DamageCalc.Type type;
    private final Dmg dmg;
    private final int basepower;
    private final String description;


    public Moves(String name, DamageCalc.Type type, Dmg dmg, int bp, String description) {
        this.name = name;
        this.type = type;
        this.dmg = dmg;
        this.basepower = bp;
        this.description = description;

    }
    public int getBasepower() {
        return basepower;
    }
    public DamageCalc.Type getType() {
        return type;
    }

    public Dmg getDmg() {
        return dmg;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
