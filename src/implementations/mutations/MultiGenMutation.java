package implementations.mutations;

import character.CharacterImpl;
import equipment.Equipment;
import interfaces.Character;
import interfaces.Mutation;
import interfaces.RoleGame;
import utilities.MutationUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiGenMutation implements Mutation {

    @Override
    public Character mutate(Character c, RoleGame rg) {

        Random r = new Random();

        //ESTO
        double prob = r.nextDouble();
        if(prob > rg.getPm()){
            return c;
        }

        Character mutated = new CharacterImpl();
        mutated.setCharacterClass(c.getCharacterClass());

        int bound = r.nextInt(7);
        List<Integer> genes = new ArrayList<>();

        while(genes.size()<bound){
            genes.add(r.nextInt(6));
        }

        if(genes.contains(5)){
            mutated.setHeight(MutationUtilities.mutateHeight(c.getHeight()));
        }
        else{
            mutated.setHeight(c.getHeight());
        }

        List<List<Equipment>> l = MutationUtilities.getList(rg);

        List<Equipment> equipment = new ArrayList<>();

        for (int i = 0; i < 5;i++){
            if(genes.contains(i) /*&& r.nextDouble()<rg.getPm() O ESTO?*/){
                equipment.add(l.get(i).get(r.nextInt(l.get(i).size())));
            }
            else {
                equipment.add(c.getEquipment().get(i));
            }
        }

        mutated.setEquipment(equipment);

        return mutated;
    }


}
