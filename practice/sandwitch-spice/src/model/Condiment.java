package model;

public class Condiment {

    private String name;

    public Condiment() {
    }

    public Condiment(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Condiment{" +
            "name='" + name + '\'' +
            '}';
    }
}
