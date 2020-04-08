package classes;

import interfaces.Class;

public class Archer implements Class {

    @Override
    public double getPerformance(double attack, double defense) {
        return 0.9 * attack + 0.1 * defense;
    }

}
