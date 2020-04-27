package implementations.implementation;

import interfaces.Character;
import interfaces.Implementation;
import interfaces.Selector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FillParentImplementation implements Implementation {

    @Override
    public List<Character> selectNextGeneration(List<Character> parents, List<Character> children, int size, Selector selector1, Selector selector2, double b) {
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
        int ceilSize = (int)Math.ceil(b*(size-childrenSize));

        toReturn.addAll(selector1.select(parents, ceilSize));
        toReturn.addAll(selector2.select(parents, size-ceilSize));

        return toReturn;
    }

    @Override
    public String toString() {
        return "Fill parent";
    }
}
