package implementations.mutations;

import character.CharacterImpl;
import equipment.Equipment;
import implementations.mutationStyles.RandomizedMutation;
import interfaces.Mutation;
import interfaces.MutationStyle;
import interfaces.RoleGame;
import interfaces.Character;
import utilities.MutationUtilities;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IndividualGenMutation implements Mutation {

    @Override
    public Character mutate(Character c, RoleGame rg, MutationStyle ms) {

        Random r = new Random();
        double prob = r.nextDouble();
        if(prob > rg.getPm()){
            return c;
        }

        Character mutated = new CharacterImpl();
        mutated.setCharacterClass(c.getCharacterClass());

        int gen = r.nextInt(6);

        if(gen == 5){
            mutated.setHeight(MutationUtilities.mutateHeight(c.getHeight()));
            mutated.setEquipment(c.getEquipment());
            return mutated;
        }
        else{
            mutated.setHeight(c.getHeight());
        }

        //List<List<Equipment>> l = MutationUtilities.getList(rg);

        List<Equipment> equipment = new ArrayList<>();

        for (int i = 0; i < 5;i++){
            if(gen == i){
//                equipment.add(l.get(i).get(r.nextInt(l.get(i).size())));
                equipment.add(ms.getMutatedEquipment(c.getEquipment().get(i),rg,i));
            }
            else {
                equipment.add(c.getEquipment().get(i));
            }
        }

        mutated.setEquipment(equipment);

        return mutated;
    }
}
