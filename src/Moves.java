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
    private final Who self;
    //private TurnHandler turnHandler;

    public String ppToString(){
        return ""+ this.pp + "/" + this.maxpp + " PP";
    }

    public Moves( String name, DamageCalc.Type type, Dmg dmg, int bp, int maxpp, Status status, Who self, String description) {
        this.name = name;
        this.type = type;
        this.dmg = dmg;
        this.basepower = bp;
        this.maxpp = maxpp;
        this.pp = maxpp;
        this.description = description;
        this.status = status;
        this.self = self;
        //this.turnHandler = turnHandler;

    }

    public int getPp() {
        return pp;
    }

    public Who getSelf() {
        return self;
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
    public void reset(){
        pp = maxpp;
    }
    //idea for implementing
    public void doMove(Summons user, Summons nonUser) {
        System.out.print("\n\n");
    switch (self){
        case Opposite ->{
            int x = damageCalc(user, nonUser);
            nonUser.setHp(nonUser.getHp()-x);
            System.out.printf("%s did %d damage to %s!\n", user.getName(), x, nonUser.getName());

        }
        case Self ->{
            double healingPercent = this.basepower*0.01;
            user.setHp(user.getHp()+(int)((user.getMaxHp()*healingPercent)));
            System.out.printf("%s healed %d damage to itself!\n", user.getName(), (int)((user.getMaxHp()*healingPercent)));


        }
        case Drain ->{
            double drain = 0.25*damageCalc(user, nonUser);
            nonUser.setHp(nonUser.getHp()-damageCalc(user, nonUser));
            user.setHp(user.getHp()+(int)drain);

            System.out.printf("%s did %d damage to %s and also healed for %d!\n", user.getName(), (int)damageCalc(user, nonUser), nonUser.getName(), (int)drain);

        }
        case Recoil ->{
            int x = damageCalc(user, nonUser);
            double ouch = 0.33*x;
            nonUser.setHp(nonUser.getHp()-damageCalc(user, nonUser));
            user.setHp(user.getHp()-(int)ouch);
            System.out.printf("%s did %d damage to %s, but hurt itself by %d!\n", user.getName(), x, nonUser.getName(), (int)ouch);


        }
        case Both ->{
            int x = damageCalc(user, nonUser);
            nonUser.setHp(nonUser.getHp()-damageCalc(user, nonUser));
            user.setHp(user.getHp()-(x));

            System.out.printf("%s did %d damage to %s and itself!\n", user.getName(), x, nonUser.getName());

        }
    }
    if (pp!=0){
        this.pp--;
    }
    }

    public int damageCalc(Summons user, Summons nonUser) {




        double other = 1.0; //used if passives are implemented
        double damage;
        double ADratio;

        double burnMult = 1.0;
        if (user.hasStatus(Status.Burn)>0){
            burnMult = 0.5;
        }
        double stab = 1.0;
        if ((user.getType1() == this.type)||(user.getType2() == this.type)){
            stab = 1.5;
        }

        damage = (44.0/50.0)*this.getBasepower();
        if (this.getDmg()== Dmg.Atk){
            ADratio =  (user.getAtk()*user.getBuff(Status.Atk) /(nonUser.getDef()*nonUser.getBuff(Status.Def)));
            damage *= burnMult * ADratio;
        } else {
            ADratio = (user.getSpatk()*user.getBuff(Status.SpAtk) /(nonUser.getSpdef()*nonUser.getBuff(Status.SpDef)));
            damage *= ADratio;
        }


        damage *=  other;
        damage *= stab;
        DamageCalc calc = new DamageCalc(this, nonUser);
        double type1 = calc.getEffectiveness();
        double type2 = calc.getEffectiveness2();
        double Eff = type1 * type2;

        damage *=Eff;
        return (int) damage;
    }



    @Override
    public String toString() {
        return  name + "\t" + type + "\t" + dmg + "\t" + basepower + "\t" + ppToString();
    }
}
