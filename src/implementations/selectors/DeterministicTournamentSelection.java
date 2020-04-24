package implementations.selectors;

import interfaces.Character;
import interfaces.Selector;
import utilities.Tournament;

import java.util.ArrayList;
import java.util.List;

public class DeterministicTournamentSelection implements Selector {

    @Override
    public List<Character> select(List<Character> population, int limit) {

        /* Ingredientes */
        int tournamentSize = (int) Math.floor((double) population.size() / limit);
        boolean hasExtraCharacters = tournamentSize * limit < population.size();
        List<Tournament> tournaments = new ArrayList<>();
        List<Character> winners = new ArrayList<>();
        int notIncludedIndex = 0;
        Tournament t;

        /* Creamos los torneos con el tamaño que calculamos */
        for(int i = 0; i < population.size(); i += tournamentSize){

            /* Esta condicion es para que la cantidad de torneos sea tal que solo se compita 1 vez */
            if(i + tournamentSize >= population.size()){
                notIncludedIndex = i;
                break;
            }

            t = new Tournament();

            for(int j = 0; j < tournamentSize; j++){
                t.addCharacter(population.get(i));
                i++;
            }

            tournaments.add(t);
        }

        /* Si tenemos personajes que no entraron a ningun torneo por la condicion, los metemos */
        if(hasExtraCharacters){
            int j = 0;
            for(int i = notIncludedIndex; i < population.size(); i++){
                tournaments.get(j).addCharacter(population.get(i));
                j++;
            }
        }

        /* Vemos los ganadores */
        for(Tournament tournament : tournaments){
            winners.add(tournament.getWinner());
        }

        return winners;
    }


    @Override
    public String toString() {
        return "Deterministic Tournament";
    }
}
