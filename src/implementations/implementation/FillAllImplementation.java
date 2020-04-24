package implementations.implementation;

import interfaces.Character;
import interfaces.Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FillAllImplementation implements Implementation {

    @Override
    public List<Character> selectNextGeneration(List<Character> parents, List<Character> children, int size) {
        List<Character> toReturn = new ArrayList<>();
        List<Character> allCharactersList = new ArrayList<>(parents);
        allCharactersList.addAll(children);
        int index, allCharacterListSize = allCharactersList.size();
        Random random = new Random(System.currentTimeMillis());

        for(int i = 0; i < size; i++){
            index = random.nextInt(allCharacterListSize);
            toReturn.add(allCharactersList.get(index));
        }

        return toReturn;
    }

    @Override
    public String toString() {
        return "Fill all";
    }
}
