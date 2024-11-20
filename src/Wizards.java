import java.util.ArrayList;
public class Wizards {
    private String name;
    public ArrayList<Summons> summons;
    public Summons lead;

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
        this.lead = summons.get(0);
    }

    @Override
    public String toString() {
        String output = "Wizard ";
        output += this.name + "\n";
        output += SummonsToString();
        return output;
    }
    public String SummonsToString(){
        String output = ""; // Initialize the output as an empty string

        for (int i = 0; i < this.summons.size(); i++) {
            output += (i+1) + ". " + this.summons.get(i) + "\n";
            for(int j = 0; j < this.summons.get(i).moves.size(); j++){
            output += "\t" + (j+1) + ". " + this.summons.get(i).moves.get(j).toString() + "\t\t\t\t\t\t";
            if(j%2==1){
                output += "\n\t"+ this.summons.get(i).moves.get(j-1).getDescription() + "\t\t\t\t\t\t" + this.summons.get(i).moves.get(j).getDescription()+"\n";
            }
            }
        }
        return output;
    }


    public void setLead(Summons lead) {
        this.lead = lead;
    }
}
