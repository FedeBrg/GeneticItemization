package implementations.criterias;

import interfaces.Criteria;
import interfaces.RoleGame;

public class AcceptableSolutionCriteria implements Criteria {

    @Override
    public void start() {}

    @Override
    public boolean check(RoleGame rg) {
        return rg.getCurrentGenerationPerformance() >= rg.getTargetPopulationPerformance();
    }

}
