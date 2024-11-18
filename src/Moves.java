public class Moves {
    public enum Type {
        Bug, Dragon, Electric, Fighting,
        Fire, Flying, Ghost, Grass, Ground,
        Ice, Normal, Poison, Psychic, Rock, Water
    }
    public enum Dmg { Atk, Spatk}

    private String name;
    public Type type;
    private Dmg dmg;
    private String description;

    public Moves(String name, Type type, Dmg dmg, String description) {
        this.name = name;
        this.type = type;
        this.dmg = dmg;
        this.description = description;

    }

    public Type getType() {
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
