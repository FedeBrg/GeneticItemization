package utilities;

import equipment.Equipment;
import interfaces.RoleGame;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MutationUtilities {

    public static double mutateHeight(double height){
        Random r = new Random();
        double prob = r.nextDouble();
        double mutatedHeight = height;
        if(prob > 0.5){
            mutatedHeight -=0.1*height;
        }
        else{
            mutatedHeight += 0.1*height;
        }

        if(mutatedHeight>2){
            return 2;
        }
        else if(mutatedHeight < 1.3){
            return 1.3;
        }

        return mutatedHeight;
    }

    public static List<List<Equipment>> getList(RoleGame rg){
        List<List<Equipment>> l = new ArrayList<>();
        l.add(rg.getWeapons());
        l.add(rg.getBoots());
        l.add(rg.getHelmets());
        l.add(rg.getGloves());
        l.add(rg.getChestplates());

        return l;
    }
}
