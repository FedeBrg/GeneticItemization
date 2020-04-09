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

public class UniformMultiGenMutation implements Mutation {
    @Override
    public Character mutate(Character c, RoleGame rg) {

        Random r = new Random();
        Character mutated = new CharacterImpl();
        mutated.setCharacterClass(c.getCharacterClass());

        if(r.nextDouble() < rg.getPm()){
            mutated.setHeight(MutationUtilities.mutateHeight(c.getHeight()));
        }
        else{
            mutated.setHeight(c.getHeight());
        }

        List<List<Equipment>> l = MutationUtilities.getList(rg);

        List<Equipment> equipment = new ArrayList<>();

        for (int i = 0; i < 5;i++){
            if(r.nextDouble() < rg.getPm()){
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
