package implementations.selectors;

import character.Character;
import interfaces.Selector;
import utilities.CharacterWithRelativePerformance;

import java.util.ArrayList;
import java.util.List;

public class RouletteSelection implements Selector {

    @Override
    public List<Character> select(List<Character> population, int limit) {

        /* Las cosas que necesitamos */
        double randomNumber, accumulatedPerformance, totalPerformance = getTotalPerformance(population);
        int index;
        List<CharacterWithRelativePerformance> orderedList = new ArrayList<>();
        List<Character> toReturn = new ArrayList<>();

        /* Lista de personajes con la performance relativa */
        for(Character c : population){
            orderedList.add(new CharacterWithRelativePerformance(c, c.getPerformance()/totalPerformance));
        }

        /* Ruleta time */
        for(int i = 0; i < limit; i++){
            randomNumber = generateRandomNumber();
            index = 0;
            accumulatedPerformance = 0;

            while(accumulatedPerformance < randomNumber){
                accumulatedPerformance += orderedList.get(index).getRelativePerformance();
                index++;
            }

            toReturn.add(orderedList.get(index).getCharacter());
        }

        return toReturn;
    }

    /* Generar un numero random entre [0, 1) */
    private double generateRandomNumber(){
        double lower = 0;
        double upper = 1.0;
        return Math.random() * (upper - lower) + lower;
    }

    /* Obtener la performance total */
    private double getTotalPerformance(List<Character> population){
        double toReturn = 0;

        for(Character c : population){
            toReturn += c.getPerformance();
        }

        return toReturn;
    }
}
