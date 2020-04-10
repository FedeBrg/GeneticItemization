package utilities;

import interfaces.Character;

import java.util.List;

public class SelectorUtilities {

    /* Obtener la performance total */
    public double getTotalPerformance(List<Character> population){
        double toReturn = 0;
        for(Character c : population){
            toReturn += c.getPerformance();
        }

        return toReturn;
    }

    /* Generar un numero random entre [0, 1) */
    public double generateRandomNumber(double lower, double upper){
        return Math.random() * (upper - lower) + lower;
    }

    /* Generar una temperatura a partir de una */
    public double calculateNewTemperature(double temperature){
        return generateRandomNumber(0.95, 0.99) * temperature;
    }

    /* Calcula e^(f(t)/T) */
    public double getBoltzmannFunction(Character c, double temperature) {
        return Math.exp(c.getPerformance() / temperature);
    }
}
