package classes;

import equipment.Equipment;
import interfaces.Class;

import java.util.List;

abstract class Character implements Class {
    private List<Equipment> equipment;
    private double attack;
    private double defense;
    private double height;

    @Override
    public double getAttack() {
        return attack;
    }

    @Override
    public double getDefense() {
        return defense;
    }

    @Override
    public double getPerformance(){ return 0; }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public void calculateAttack() {
        double atm = 0.7 - Math.pow((3 * height - 5), 4) + Math.pow((3 * height - 5), 2) + height / 4;
        double s = 0, a = 0, e = 0;

        for(Equipment item : equipment){
            s += item.getStrength();
            a += item.getAgility();
            e += item.getExpertise();
        }

        s = 100 * Math.tanh(0.01 * s);
        a = Math.tanh(0.01 * a);
        e = 0.6 * Math.tanh(0.01 * e);

        attack = (a + e) * s * atm;
    }

    @Override
    public void calculateDefense(){
        double dem = 1.9 + Math.pow((2.5 * height - 4.16), 4) - Math.pow((2.5 * height - 4.16), 2) - (3 * height) / 10;
        double e = 0, r = 0, h = 0;

        for(Equipment item : equipment){
            e += item.getExpertise();
            r += item.getResistance();
            h += item.getHealth();
        }

        e = 0.6 * Math.tanh(0.01 * e);
        r = Math.tanh(0.01 * r);
        h = 100 * Math.tanh(0.01 * h);

        defense = (r + e) * h * dem;
    }

    @Override
    public void addEquipment(Equipment e){
        equipment.add(e);
    }

    @Override
    public List<Equipment> getEquipment(){
        return equipment;
    }

    @Override
    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    @Override
    public void setAttack(double attack) {
        this.attack = attack;
    }

    @Override
    public void setDefense(double defense) {
        this.defense = defense;
    }

    @Override
    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public void printCharacter(){
        System.out.printf("HEIGHT = %f, ATTACK = %f, DEFENSE = %f\n", height, attack, defense);
    }
}
