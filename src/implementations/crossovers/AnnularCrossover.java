package implementations.crossovers;

import character.CharacterImpl;
import equipment.Equipment;
import interfaces.Character;
import interfaces.Crossover;
import interfaces.Mutation;

import java.util.*;

public class AnnularCrossover implements Crossover {

    @Override
    public Map.Entry<Character, Character> cross(Character c1, Character c2) {

        Character mutated1 = new CharacterImpl();
        Character mutated2 = new CharacterImpl();

        mutated1.setCharacterClass(c1.getCharacterClass());
        mutated2.setCharacterClass(c1.getCharacterClass());

        List<Equipment> e1 = new ArrayList<>(c1.getEquipment());
        List<Equipment> e2 = new ArrayList<>(c2.getEquipment());

        mutated1.setHeight(c1.getHeight());
        mutated2.setHeight(c2.getHeight());

        Random r = new Random();
        int crossPoint = r.nextInt(6);
        int length = r.nextInt(4);
        int i = crossPoint;

        while(length>0){
            if(i == 5){
                mutated1.setHeight(c2.getHeight());
                mutated2.setHeight(c1.getHeight());
                i = 0;
            }
            else{
                e1.set(i,c2.getEquipment().get(i));
                e2.set(i,c1.getEquipment().get(i));
                i++;
            }

            length--;
        }


        mutated1.setEquipment(e1);
        mutated2.setEquipment(e2);

        return new AbstractMap.SimpleEntry<>(mutated1,mutated2);
    }

    @Override
    public String toString() {
        return "Annular";
    }
}
