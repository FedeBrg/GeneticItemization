package utilities;

import java.util.ArrayList;
import java.util.List;
import interfaces.Character;

public class Tournament {
    private List<Character> characters;

    public Tournament() {
        this.characters = new ArrayList<>();
    }

    public void addCharacter(Character c){
        characters.add(c);
    }

    public Character getWinner(){
        Character toReturn = characters.get(0);

        for(int i = 1; i < characters.size(); i++){
            if(characters.get(i).getPerformance() > toReturn.getPerformance()){
                toReturn = characters.get(i);
            }
        }

        return toReturn;
    }
}
