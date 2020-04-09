package implementations.selectors;

import interfaces.Character;
import interfaces.Selector;
import utilities.SelectorUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProbabilisticTournamentSelection implements Selector {

    @Override
    public List<Character> select(List<Character> population, int limit) {

        /* Ingredientes */
        double random, threshold;
        boolean betterPick;
        SelectorUtilities su = new SelectorUtilities();
        List<Character> winners  = new ArrayList<>();
        int i, j, size = population.size();

        /* Competencias entre dos */
        while(true){
            random = su.generateRandomNumber(0,1);
            threshold = su.generateRandomNumber(0.5,1);
            betterPick = random < threshold;
            i = generateRandomIndex(size);
            j = generateRandomIndex(size);
            winners.add(calculateWinner(population.get(i), population.get(j), betterPick));

            /* Alcanzamos la cantidad pedida y nos vamos */
            if(winners.size() == limit){
                return winners;
            }
        }
    }

    /* Elige el ganador de los competidores */
    private Character calculateWinner(Character c1, Character c2, boolean betterPick) {
        if(betterPick){
            return (c1.getPerformance() > c2.getPerformance())? c1 : c2;
        }
        else{
            return (c1.getPerformance() > c2.getPerformance())? c2 : c1;
        }
    }

    private int generateRandomIndex(int size){
        Random r = new Random();
        return Math.abs(r.nextInt()) % size;
    }

}
