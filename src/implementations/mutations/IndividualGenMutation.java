package implementations.mutations;

import character.CharacterImpl;
import equipment.Equipment;
import interfaces.Mutation;
import interfaces.RoleGame;
import interfaces.Character;
import utilities.MutationUtilities;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IndividualGenMutation implements Mutation {

    @Override
    public Character mutate(Character c, RoleGame rg) {

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

        List<List<Equipment>> l = new ArrayList<>();
        l.add(rg.getWeapons());
        l.add(rg.getBoots());
        l.add(rg.getHelmets());
        l.add(rg.getGloves());
        l.add(rg.getChestplates());

        List<Equipment> equipment = new ArrayList<>();

        for (int i = 0; i < 5;i++){
            if(gen == i){
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
