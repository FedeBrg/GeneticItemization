package classes;

import interfaces.Class;

public class Support  implements Class {

    public double getPerformance(double attack, double defense) {
        return 0.3 * attack + 0.8 * defense;
    }

}
