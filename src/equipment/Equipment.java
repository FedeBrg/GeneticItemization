package equipment;

public class Equipment implements Comparable<Equipment>{
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

    public double getQuality(){
        return strength+agility+expertise+resistance+health;
    }


    @Override
    public int compareTo(Equipment o) {
        double diff = getQuality()-o.getQuality();
        if(diff == 0){
            return 0;
        }
        return diff>0?1:-1;
    }
}
