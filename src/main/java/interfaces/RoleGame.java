package interfaces;

import equipment.Equipment;

import java.util.List;
import java.util.TreeSet;

public interface RoleGame {

     List<Equipment> getWeapons();
     List<Equipment> getBoots();
     List<Equipment> getHelmets();
     List<Equipment> getGloves();
     List<Equipment> getChestplates();
     TreeSet<Equipment> getWeaponsTree();
     TreeSet<Equipment> getBootsTree();
     TreeSet<Equipment> getHelmetsTree();
     TreeSet<Equipment> getGlovesTree();
     TreeSet<Equipment> getChestplatesTree();

     double getPm();
     long getStopTime();
     int getMaxGeneration();
     int getCurrentGeneration();
     int getGenerationsNotChanging();
     double getCurrentGenerationPerformance();
     void setCurrentGenerationPerformance(double currentGenerationPerformance);
     double getTolerance();
     double getBestPerformance();
     void setBestPerformance(double bestPerformance);
     double getTargetPopulationPerformance();

}
