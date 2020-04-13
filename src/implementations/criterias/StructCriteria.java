package implementations.criterias;

import interfaces.Criteria;
import interfaces.RoleGame;

/*
    La idea es que si el desempeño general entre varias generaciones no varia de una forma significativa,
    entonces la poblacion no cuenta con diferencias de equipamiento y estadisticas significativas.
    Si este suceso se reitera a lo largo de un numero de generaciones parametrizable entonces no deberia
    mucho si dejamos correr el tiempo, por lo tanto, cortamos antes.
*/

public class StructCriteria implements Criteria {
    private int generationsNotChanging;
    private double lastGenerationPerformance;

    @Override
    public void start() {
        generationsNotChanging = 0;
        lastGenerationPerformance = 0;
    }

    @Override
    public boolean check(RoleGame rg) {
        if(Math.abs(rg.getCurrentGenerationPerformance() - lastGenerationPerformance) < rg.getTolerance()){
            generationsNotChanging++;
        }

        else{
            generationsNotChanging = 0;
        }

        lastGenerationPerformance = rg.getCurrentGenerationPerformance();
        return generationsNotChanging == rg.getGenerationsNotChanging();
    }
}
