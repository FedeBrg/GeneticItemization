package implementations.implementation;

import interfaces.Character;
import interfaces.Implementation;
import interfaces.Selector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FillAllImplementation implements Implementation {

    @Override
    public List<Character> selectNextGeneration(List<Character> parents, List<Character> children, int size, Selector selector1, Selector selector2, double b) {
        List<Character> toReturn = new ArrayList<>();
        List<Character> allCharactersList = new ArrayList<>(parents);
        allCharactersList.addAll(children);

        int ceilSize = (int)Math.ceil(b*size);

        toReturn.addAll(selector1.select(allCharactersList, ceilSize));
        toReturn.addAll(selector2.select(allCharactersList, size-ceilSize));

        return toReturn;
    }

    @Override
    public String toString() {
        return "Fill all";
    }
}
