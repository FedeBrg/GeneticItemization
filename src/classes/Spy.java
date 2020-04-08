package classes;

import java.util.ArrayList;

public class Spy extends Character {

    public Spy(){
        setEquipment(new ArrayList<>());
    }

    @Override
    public double getPerformance() {
        return 0.8 * getAttack() + 0.3 * getDefense();
    }

}
