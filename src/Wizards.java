import java.util.ArrayList;
public class Wizards {
    private String name;
    private ArrayList<Summons> summons;

    public Wizards(String name){
        this.name = name;
        this.summons = new ArrayList<>();
    }

    public String getName(){
        return this.name;
    }


    public ArrayList<Summons> getSummons(){
        return this.summons;

    }

    public void addSummons(Summons s){
        this.summons.add(s);
    }

    @Override
    public String toString() {
        String output = "Trainer ";
        output += this.name + "\n";
        output += "Summons:\n";
        for (int i = 0; i < this.summons.size(); i++) {
            output += this.summons.get(i).toString() + "\n";
        }
        return output;
    }
}

