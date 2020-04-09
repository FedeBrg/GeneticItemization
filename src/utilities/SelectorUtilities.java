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
    public double generateRandomNumber(){
        double lower = 0;
        double upper = 1.0;
        return Math.random() * (upper - lower) + lower;
    }

}
