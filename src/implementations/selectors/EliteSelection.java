package implementations.selectors;

import interfaces.Selector;
import classes.Character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EliteSelection implements Selector {

    @Override
    public List<Character> select(List<Character> population, int limit) {

        /* Creamos las cosas que necesitamos */
        int count = 0, j = 0, n, populationSize = population.size();
        List<Character> toReturn = new ArrayList<>();
        Character c;

        /* Dejamos ordenada la lista en orden descendiente */
        population.sort((o1, o2) -> (int) Math.floor(o1.getPerformance() - o2.getPerformance()));
        Collections.reverse(population);

        /* Agarramos n veces cada personita */
        while(count < limit){
            n = (int) Math.ceil((double)(limit - j)/populationSize);
            c = population.get(j);

            for(int t = 0; t < n; t++){
                toReturn.add(c);
            }

            count += n;
            j++;
        }

        return toReturn;
    }

}
