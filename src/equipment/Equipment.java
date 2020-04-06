package equipment;

public class Equipment {
    private int id;
    private double strength;
    private double agility;
    private double expertise;
    private double resistance;
    private double health;

    public Equipment(int id, double strength, double agility, double expertise, double resistance, double health) {
        this.id = id;
        this.strength = strength;
        this.agility = agility;
        this.expertise = expertise;
        this.resistance = resistance;
        this.health = health;
    }

    public int getId(){
        return id;
    }

    public double getStrength() {
        return strength;
    }

    public double getAgility() {
        return agility;
    }

    public double getExpertise() {
        return expertise;
    }

    public double getResistance() {
        return resistance;
    }

    public double getHealth() {
        return health;
    }

}
