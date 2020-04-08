package classes;

import java.util.ArrayList;

public class Archer extends Character {

    public Archer(){
        setEquipment(new ArrayList<>());
    }

    @Override
    public double getPerformance() {
        return 0.9 * getAttack() + 0.1 * getDefense();
    }

}
