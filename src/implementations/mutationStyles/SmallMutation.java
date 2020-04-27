package implementations.mutationStyles;

import equipment.Equipment;
import interfaces.MutationStyle;
import interfaces.RoleGame;
import utilities.MutationUtilities;

import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class SmallMutation implements MutationStyle {

    @Override
    public Equipment getMutatedEquipment(Equipment e, RoleGame rg, int i) {

        List<TreeSet<Equipment>> l = MutationUtilities.getTrees(rg);

        Random r = new Random();

        if(r.nextDouble()>0.5){
            Equipment newEq =  l.get(i).higher(e);
            if(newEq == null){
                return l.get(i).lower(e);

            }
            else{
                return newEq;
            }
        }
        else{
            Equipment newEq =  l.get(i).lower(e);
            if(newEq == null){
                return l.get(i).higher(e);
            }
            else{
                return newEq;
            }
        }

    }

    @Override
    public String toString() {
        return "Small delta";
    }
}
