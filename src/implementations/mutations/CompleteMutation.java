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

public class CompleteMutation implements Mutation {

    @Override
    public Character mutate(Character c, RoleGame rg) {

        Random r = new Random();


        if(r.nextDouble() > rg.getPm()){
            return c;
        }

        Character mutated = new CharacterImpl();
        mutated.setCharacterClass(c.getCharacterClass());
        mutated.setHeight(MutationUtilities.mutateHeight(c.getHeight()));

        List<List<Equipment>> l = MutationUtilities.getList(rg);

        List<Equipment> equipment = new ArrayList<>();

        for (int i = 0; i < 5;i++){
            equipment.add(l.get(i).get(r.nextInt(l.get(i).size())));
        }

        mutated.setEquipment(equipment);

        return mutated;

    }

}
