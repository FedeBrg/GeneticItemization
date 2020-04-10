package implementations.selectors;

import interfaces.Character;
import interfaces.Selector;
import utilities.CharacterWithFixedPerformance;
import utilities.CharacterWithRelativePerformance;
import utilities.SelectorUtilities;

import java.util.ArrayList;
import java.util.List;

public class BoltzmannSelection implements Selector {

    @Override
    public List<Character> select(List<Character> population, int limit) {

        /* Juntamos en un bowl los ingredientes */
        List<CharacterWithFixedPerformance> newPerformancePopulation = new ArrayList<>();
        List<CharacterWithRelativePerformance> orderedList = new ArrayList<>();
        List<Character> toReturn = new ArrayList<>();
        SelectorUtilities su = new SelectorUtilities();
        double randomNumber, accumulatedPerformance, functionValue;
        double totalPerformance = 0, avgValue = 0, t = population.size();
        int index;

        /* Dejamos ordenada la lista en orden creciente */
        population.sort((o1, o2) -> (int) Math.floor(o1.getPerformance() - o2.getPerformance()));

        /* Hacemos la nueva funcion de performance sin dividir por el promedio */
        for (Character c : population) {
            functionValue = su.getBoltzmannFunction(c,t);
            t = su.calculateNewTemperature(t);
            avgValue += functionValue;
            newPerformancePopulation.add(new CharacterWithFixedPerformance(c, functionValue));
        }

        /* Calculamos el promedio de las e^(f(t)/L) */
        avgValue = avgValue / newPerformancePopulation.size();

        /* Actualizamos los valores de la lista con la performance actualizada, divido por promedio */
        for(CharacterWithFixedPerformance character : newPerformancePopulation){
            character.setFixedPerformance(character.getFixedPerformance() / avgValue);
            totalPerformance += character.getFixedPerformance();
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
