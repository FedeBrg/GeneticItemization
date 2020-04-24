package implementations.implementation;

import interfaces.Character;
import interfaces.Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FillParentImplementation implements Implementation {

    @Override
    public List<Character> selectNextGeneration(List<Character> parents, List<Character> children, int size) {
        List<Character> toReturn = new ArrayList<>();
        int childrenSize = children.size();
        Random random = new Random(System.currentTimeMillis());

        if(size < childrenSize){
            for(int i = 0; i < size; i++){
                toReturn.add(children.get(Math.abs(random.nextInt()) % childrenSize));
            }

            return toReturn;
        }

        toReturn.addAll(children);
        int parentSize = parents.size();

        for(int i = toReturn.size(); i < size; i++){
            toReturn.add(parents.get(Math.abs(random.nextInt()) % parentSize));
        }

        return toReturn;
    }

}
