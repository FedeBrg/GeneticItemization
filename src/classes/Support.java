package classes;

import java.util.ArrayList;

public class Support extends Character {

    public Support(){
        setEquipment(new ArrayList<>());
    }

    @Override
    public double getPerformance() {
        return 0.3 * getAttack() + 0.8 * getDefense();
    }

}
