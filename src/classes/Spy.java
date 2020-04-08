package classes;

import interfaces.Class;


public class Spy implements Class {
    @Override
    public double getPerformance(double attack, double defense) {
        return 0.8 * attack + 0.3 * defense;
    }

}
