package implementations.selectors;

import interfaces.Character;
import interfaces.Selector;
import utilities.CharacterWithRelativePerformance;
import utilities.SelectorUtilities;

import java.util.ArrayList;
import java.util.List;

public class RouletteSelection implements Selector {

    @Override
    public List<Character> select(List<Character> population, int limit) {

        /* Las cosas que necesitamos */
        double randomNumber, accumulatedPerformance, totalPerformance = getTotalPerformance(population);
        int index;
        SelectorUtilities su = new SelectorUtilities();
        List<CharacterWithRelativePerformance> orderedList = new ArrayList<>();
        List<Character> toReturn = new ArrayList<>();

        /* Lista de personajes con la performance relativa */
        for(Character c : population){
            orderedList.add(new CharacterWithRelativePerformance(c, c.getPerformance()/totalPerformance));
        }

        /* Ruleta time */
        for(int i = 0; i < limit; i++){
            randomNumber = su.generateRandomNumber(0, 1);
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
        }

        return toReturn;
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
