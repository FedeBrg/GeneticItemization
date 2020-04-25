package implementations.selectors;

import interfaces.Character;
import interfaces.Selector;
import utilities.CharacterWithRelativePerformance;

import java.util.ArrayList;
import java.util.List;

public class UniversalSelection implements Selector {
    private int j = 0;

    @Override
    public List<Character> select(List<Character> population, int limit) {
        this.j = 0;

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
            randomNumber = generateRandomNumber(limit);
            accumulatedPerformance = orderedList.get(0).getRelativePerformance();

            /* El caso base debe sacarse aparte */
            if(accumulatedPerformance > randomNumber){
                toReturn.add(orderedList.get(0).getCharacter());
            }

            /* El resto de los casos pueden resolverse con un while */
            else {
                index = 1;

                while(accumulatedPerformance <= randomNumber && index < orderedList.size() - 1){
                    accumulatedPerformance += orderedList.get(index).getRelativePerformance();
                    index++;
                }

                toReturn.add(orderedList.get(index).getCharacter());
            }

            j++;
        }

        return toReturn;
    }

    /* Generar el numero random para Universal */
    private double generateRandomNumber(int limit){
        double lower = 0;
        double upper = 1.0;
        double r = Math.random() * (upper - lower) + lower;

        return (r + j)/limit;
    }

    /* Obtener la performance total */
    private double getTotalPerformance(List<Character> population){
        double toReturn = 0;

        for(Character c : population){
            toReturn += c.getPerformance();
        }

        return toReturn;
    }

    @Override
    public String toString() {
        return "Universal";
    }
}
