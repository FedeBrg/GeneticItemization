package implementations.selectors;

import interfaces.Character;
import interfaces.Selector;
import utilities.CharacterWithFixedPerformance;
import utilities.CharacterWithRelativePerformance;
import utilities.SelectorUtilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RankingSelection implements Selector {

    @Override
    public List<Character> select(List<Character> population, int limit) {

        /* Cosas que necesitamos */
        List<CharacterWithFixedPerformance> newPerformancePopulation = new ArrayList<>();
        List<CharacterWithRelativePerformance> orderedList = new ArrayList<>();
        List<Character> toReturn = new ArrayList<>();
        SelectorUtilities su = new SelectorUtilities();
        double randomNumber, accumulatedPerformance, totalPerformance = 0;
        int index, size = population.size();
        Character c;

        /* Dejamos ordenada la lista en orden descendiente (ranking) */
        population.sort((o1, o2) -> (int) Math.floor(o1.getPerformance() - o2.getPerformance()));
        Collections.reverse(population);

        /* Ahora con el ranking hacemos la nueva funcion de performance */
        for(int i = 1; i <= size; i++){
            c = population.get(i - 1);
            totalPerformance += (double) (size - i) / size;
            newPerformancePopulation.add(new CharacterWithFixedPerformance(c, (double) (size - i) / size));
        }

        /* Lista de personajes con la performance relativa nueva */
        for(CharacterWithFixedPerformance modifiedCharacter : newPerformancePopulation){
            orderedList.add(new CharacterWithRelativePerformance(modifiedCharacter.getCharacter(),
                    modifiedCharacter.getFixedPerformance()/totalPerformance));
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

}
