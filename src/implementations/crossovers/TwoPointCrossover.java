package implementations.crossovers;

import character.CharacterImpl;
import equipment.Equipment;
import interfaces.Character;
import interfaces.Crossover;

import java.util.*;

public class TwoPointCrossover implements Crossover {

    @Override
    public Map.Entry<Character, Character> cross(Character c1, Character c2) {

        Character mutated1 = new CharacterImpl();
        Character mutated2 = new CharacterImpl();

        mutated1.setCharacterClass(c1.getCharacterClass());
        mutated2.setCharacterClass(c1.getCharacterClass());

        List<Equipment> e1 = new ArrayList<>();
        List<Equipment> e2 = new ArrayList<>();

        Random r = new Random();
        int r1 = r.nextInt(7);
        int r2 = r.nextInt(7);

        int crossPoint = Math.min(r1,r2);
        int endPoint = Math.max(r1,r2);


        for(int i = 0; i < 5; i++){
            if(i<crossPoint || i>=endPoint){
                e1.add(c1.getEquipment().get(i));
                e2.add(c2.getEquipment().get(i));
            }
            else{
                e1.add(c2.getEquipment().get(i));
                e2.add(c1.getEquipment().get(i));
            }
        }

        if(endPoint == 6){
            mutated1.setHeight(c2.getHeight());
            mutated2.setHeight(c1.getHeight());
        }
        else if(endPoint < 6){
            mutated1.setHeight(c1.getHeight());
            mutated2.setHeight(c2.getHeight());
        }

        mutated1.setEquipment(e1);
        mutated2.setEquipment(e2);

        return new AbstractMap.SimpleEntry<>(mutated1,mutated2);
    }

    @Override
    public String toString() {
        return "Two point";
    }
}
