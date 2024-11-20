public class Moves {

    public enum Dmg { Atk, Spatk}
    public enum Status {Sun, Rain, Par, Burn, Pois, Slp, Null}

    private Status status;
    private final String name;
    public DamageCalc.Type type;
    private final Dmg dmg;
    private final int basepower;
    private final String description;
    private final int maxpp;
    private int pp;

    public Moves(String name, DamageCalc.Type type, Dmg dmg, int bp, int maxpp, Status status, String description) {
        this.name = name;
        this.type = type;
        this.dmg = dmg;
        this.basepower = bp;
        this.maxpp = maxpp;
        this.pp = maxpp;
        this.description = description;
        this.status = status;

    }

    public Status getStatus() {
        return status;
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

    @Override
    public String toString() {
        return  name + "\t" + type + "\t" + dmg + "\t" + basepower + "\t" + pp +"/" + maxpp;
    }
}
