package model;

import java.util.ArrayList;

public class Sandwich {

    private ArrayList<Condiment> condimentsList = new ArrayList<>();

    public Sandwich() {
    }

    public Sandwich(ArrayList<Condiment> condimentsList) {
        this.condimentsList = condimentsList;
    }

    public ArrayList<Condiment> getCondimentsList() {
        return condimentsList;
    }

    public void setCondimentsList(ArrayList<Condiment> condimentsList) {
        this.condimentsList = condimentsList;
    }

    public void addCondiment(Condiment condiment) {
        condimentsList.add(condiment);
    }

    @Override
    public String toString() {
        return "Sandwich{" +
            "condimentsList=" + condimentsList +
            '}';
    }
}
