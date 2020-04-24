package classes;

import interfaces.Class;


public class Warrior implements Class {

    @Override
    public double getPerformance(double attack, double defense) {
        return 0.6 * attack + 0.6 * defense;
    }

    @Override
    public String toString() {
        return "Warrior";
    }
}
