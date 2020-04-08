package interfaces;

import equipment.Equipment;

import java.util.List;

public interface Class {
    double getAttack();
    void setAttack(double attack);
    double getDefense();
    void setDefense(double defense);
    double getPerformance();
    double getHeight();
    void setHeight(double height);
    void calculateAttack();
    void calculateDefense();
    List<Equipment> getEquipment();
    void setEquipment(List<Equipment> equipment);
    void addEquipment(Equipment e);
    void printCharacter();
}
