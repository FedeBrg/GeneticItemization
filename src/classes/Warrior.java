package classes;

import java.util.ArrayList;

public class Warrior extends Character {

    public Warrior(){
        setEquipment(new ArrayList<>());
    }

    public double getPerformance() {
        return 0.6 * getAttack() + 0.6 * getDefense();
    }

}
