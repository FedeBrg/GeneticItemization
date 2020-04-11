package interfaces;

import equipment.Equipment;

import java.util.List;

public interface RoleGame {

     List<Equipment> getWeapons();
     List<Equipment> getBoots();
     List<Equipment> getHelmets();
     List<Equipment> getGloves();
     List<Equipment> getChestplates();
     double getPm();
     long getStopTime();
     int getCurrentGeneration();
     int getMaxGeneration();

}
