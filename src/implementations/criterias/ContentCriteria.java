package implementations.criterias;

import interfaces.Criteria;
import interfaces.RoleGame;

public class ContentCriteria implements Criteria {
    private double lastBestPerformance;
    private int generationsNotChanging;

    @Override
    public void start() {
        lastBestPerformance = 0;
        generationsNotChanging = 0;
    }

    @Override
    public boolean check(RoleGame rg) {
        if(Math.abs(rg.getBestPerformance() - lastBestPerformance) < rg.getTolerance()){
             generationsNotChanging++;
        }

        else{
            generationsNotChanging = 0;
        }

        lastBestPerformance = rg.getBestPerformance();
        return generationsNotChanging == rg.getGenerationsNotChanging();
    }
}
