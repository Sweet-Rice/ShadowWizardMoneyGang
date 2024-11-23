import java.util.ArrayList;
public class Wizards {
    private String name;
    private ArrayList<Summons> summons;
    private Summons getLead;

    /*
        getLead - getter for the lead summon
        @param - none
        @return - lead summon
         */
    public Summons getLead() {
        return getLead;
    }

    /*
    Wizards - constructor for the wizard
    @name - name of the wizard
     */
    public Wizards(String name){
        this.name = name;
        this.summons = new ArrayList<>();
    }

    /*
    getName - getter for the name of the wizard
    @param - none
    @return - name var
     */
    public String getName(){
        return this.name;
    }


    /*
    getSummons - getter for the list of summons of the wizard
    @param - none
    @return - the arraylist of summons
     */
    public ArrayList<Summons> getSummons(){
        return this.summons;

    }
    /*
    addSummons - adds a summon to the arraylist
    @s - the summon to be added
    @return - void
     */
    public void addSummons(Summons s){
        this.summons.add(s);
        this.getLead = summons.get(0);
    }
    /*
    removeSummons - removes a summon from the arraylist
    @s - the sumon to be removed
    @return - void
     */
    public void removeSummons(Summons s){
        this.summons.remove(s);
    }

    @Override
    //overrides the toString default method to a name and its party
    public String toString() {
        String output = "Wizard ";
        output += this.name + "\n";
        output += SummonsToString();
        return output;
    }
    /*
    SummonsToString - converts the summons array to a readable string
    @param - none
    @return - string of summons and its moves
     */
    public String SummonsToString(){
        String output = ""; // Initialize the output as an empty string

        for (int i = 0; i < this.summons.size(); i++) {
            output += (i+1) + ". " + this.summons.get(i) + "\n";
            for(int j = 0; j < this.summons.get(i).getMoves().size(); j++){
            output += "\t" + (j+1) + ". " + this.summons.get(i).getMoves().get(j).toString() + "\t\t\t\t\t\t";
            if(j%2==1){
                output += "\n\t"+ this.summons.get(i).getMoves().get(j-1).getDescription() + "\t\t\t\t\t\t" + this.summons.get(i).getMoves().get(j).getDescription()+"\n";
            }
            }
        }
        return output;
    }

    /*
    setLead - sets the lead summon
    @lead - the summon to be set as lead
    @return - void
     */
    public void setLead(Summons lead) {
        this.getLead = lead;
    }
}

