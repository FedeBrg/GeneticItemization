package character;

import equipment.Equipment;
import interfaces.Class;

import java.util.List;

public class Character {
    private List<Equipment> equipment;
    private double attack;
    private double defense;
    private double height;
    private Class characterClass;

    public Character(List<Equipment> equipment, Class characterClass, double height) {
        this.equipment = equipment;
        this.characterClass = characterClass;
        this.height = height;

        calculateAttack();
        calculateDefense();
    }

    public double getAttack() {
        return attack;
    }

    public double getDefense() {
        return defense;
    }

    public double getPerformance(){ return characterClass.getPerformance(attack,defense); }

    public double getHeight() {
        return height;
    }

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

    public void addEquipment(Equipment e){
        equipment.add(e);
    }

    public List<Equipment> getEquipment(){
        return equipment;
    }

    public void setEquipment(List<Equipment> equipment) {
        this.equipment = equipment;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }

    public void setDefense(double defense) {
        this.defense = defense;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void printCharacter(){
        System.out.printf("HEIGHT = %.4f,\t ATTACK = %.4f,\t DEFENSE = %.4f\n", height, attack, defense);
    }
}
