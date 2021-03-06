package old;

import old.EquipmentOld;

public class Boots implements EquipmentOld {
    private int id;
    private double strength;
    private double agility;
    private double expertise;
    private double resistance;
    private double health;

    public Boots(int id, double strength, double agility, double expertise, double resistance, double health) {
        this.id = id;
        this.strength = strength;
        this.agility = agility;
        this.expertise = expertise;
        this.resistance = resistance;
        this.health = health;
    }

    @Override
    public double getStrength() {
        return strength;
    }

    @Override
    public double getAgility() {
        return agility;
    }

    @Override
    public double getExpertise() {
        return expertise;
    }

    @Override
    public double getResistance() {
        return resistance;
    }

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public int getId(){
        return id;
    }
}
