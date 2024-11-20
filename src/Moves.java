public class Moves {

    public enum Dmg { Atk, Spatk}
    public enum Status {Sun, Rain, Par, Burn, Pois, Slp, Atk, SpAtk, Def, SpDef, Spd,
                            LessAtk, LessSpAtk, LessDef, LessSpDef, LessSpd, Null}
    public enum Who {Opposite, Self, Drain, Recoil, Both}


    private final Status status;
    private final String name;
    public DamageCalc.Type type;
    private final Dmg dmg;
    private final int basepower;
    private final String description;
    private final int maxpp;
    private int pp;
    private static Who self;

    public Moves(String name, DamageCalc.Type type, Dmg dmg, int bp, int maxpp, Status status, Who self, String description) {
        this.name = name;
        this.type = type;
        this.dmg = dmg;
        this.basepower = bp;
        this.maxpp = maxpp;
        this.pp = maxpp;
        this.description = description;
        this.status = status;
        this.self = self;

    }

    public Status getStatus() {
        return status;
    }

    public int getBasepower() {
        return basepower;
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

    //idea for implementing
    public static void doMove(Summons user, Summons nonUser, Moves move) {
    switch (self){
        case Opposite ->{
            int x = damageCalc(user, nonUser, move);
            System.out.printf("%s did %d damage to %s!\n", user.getName(), x, nonUser.getName());
        }
        case Self ->{
            double healingPercent = move.basepower*0.01;
            user.setHp(user.getHp()+(int)((user.getMaxHp()*healingPercent)));
            System.out.printf("%s healed %d damage to itself!\n", user.getName(), (int)((user.getMaxHp()*healingPercent)));

        }
        case Drain ->{
            double drain = 0.25*damageCalc(user, nonUser, move);
            user.setHp(user.getHp()+(int)drain);
            System.out.printf("%s did %d damage to %s and also healed for %d!\n", user.getName(), (int)drain, nonUser.getName());
        }
        case Recoil ->{
            int x = damageCalc(user, nonUser, move);
            double ouch = 0.33*x;
            user.setHp(user.getHp()-(int)ouch);
            System.out.printf("%s did %d damage to %s, but hurt itself by %d!\n", user.getName(), x, nonUser.getName(), (int)ouch);

        }
        case Both ->{
            int x = damageCalc(user, nonUser, move);
            user.setHp(user.getHp()-(x));
            System.out.printf("%s did %d damage to %s and itself!\n", user.getName(), x, nonUser.getName());
        }
    }

    }
    public static double getBuff(Summons user, Status status){
        int x = 0;
        double modifier;
        switch (status){
            case Atk ->x = user.hasStatus(Status.Atk)-user.hasStatus(Status.LessAtk);
            case SpAtk -> x = user.hasStatus(Status.SpAtk)-user.hasStatus(Status.LessSpAtk);
            case Def -> x = user.hasStatus(Status.Def)-user.hasStatus(Status.LessDef);
            case SpDef -> x = user.hasStatus(Status.SpDef)-user.hasStatus(Status.LessSpDef);
            case Spd -> x = user.hasStatus(Status.Spd)-user.hasStatus(Status.LessSpd);

        }
        if (x>=0){
            modifier = (double) (x + 2) /2;
        }else{
            x=x*(-1);
            modifier = (double) 2 /(2+x);
        }
        return modifier;
    }
    private static int damageCalc(Summons user, Summons nonUser, Moves move) {




        double other = 1.0; //used if passives are implemented
        double damage;
        double ADratio;

        double burnMult = 1.0;
        if (user.hasStatus(Status.Burn)>0){
            burnMult = 0.5;
        }
        double stab = 1.0;
        if ((user.type1 == move.type)||(user.type2 == move.type)){
            stab = 1.5;
        }

        damage = (44.0/50.0)*move.getBasepower();
        if (move.getDmg()== Dmg.Atk){
            ADratio =  (user.getAtk()*getBuff(user,Status.Atk) /(nonUser.getDef()*getBuff(nonUser,Status.Def)));
        } else {
            ADratio = (user.getSpatk()*getBuff(user, Status.SpAtk) /(nonUser.getSpdef()*getBuff(nonUser,Status.SpDef)));
        }
        damage = damage * ADratio;
        damage += 2;
        TurnHandler.WeatherHandler(TurnHandler.getWeather());
        damage *= TurnHandler.weatherMultiplier(move)*stab*
                (DamageCalc.getEffectiveness(move.type, nonUser.type1)*
                        DamageCalc.getEffectiveness(move.type, nonUser.type2));
        damage *= burnMult * other;
        nonUser.setHp(user.getHp()-(int)damage);
        toRainOrNotToRain(nonUser, move);
        return (int) damage;
    }

    private static void toRainOrNotToRain(Summons user, Moves move) {
        switch (move.status){
            case Sun -> {
                TurnHandler.setWeather(Status.Sun);
                System.out.println("The sun grew bright!");
            }
            case Rain -> {
                TurnHandler.setWeather(Status.Rain);
                System.out.println("The rain came pouring!");
            }default -> user.addStatuses(move.status);
        }
    }

    @Override
    public String toString() {
        return  name + "\t" + type + "\t" + dmg + "\t" + basepower + "\t" + pp +"/" + maxpp;
    }
}
