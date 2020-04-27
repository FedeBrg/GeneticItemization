package implementations.criterias;


import interfaces.Criteria;
import interfaces.RoleGame;

public class TimeCriteria implements Criteria {
    private long startTime;

    @Override
    public void start(){
        startTime = System.currentTimeMillis();
    }

    @Override
    public boolean check(RoleGame rg){
        return System.currentTimeMillis() - startTime >= rg.getStopTime();
    }

    @Override
    public String toString() {
        return "Time";
    }
}
