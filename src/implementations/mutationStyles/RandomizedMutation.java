package implementations.mutationStyles;

import equipment.Equipment;
import interfaces.MutationStyle;
import interfaces.RoleGame;
import utilities.MutationUtilities;

import java.util.List;
import java.util.Random;

public class RandomizedMutation implements MutationStyle {

    @Override
    public Equipment getMutatedEquipment(Equipment e, RoleGame rg, int i) {

        List<List<Equipment>> l = MutationUtilities.getList(rg);

        Random r = new Random();

        return l.get(i).get(r.nextInt(l.get(i).size()));

    }

    @Override
    public String toString() {
        return "Randomized";
    }
}
