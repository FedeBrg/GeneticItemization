package interfaces;

import equipment.Equipment;

import java.util.List;

public interface Character {

    double getAttack();
    double getDefense();

    double getPerformance();

    Class getCharacterClass();

    void setCharacterClass(Class characterClass);
    double getHeight();

    void calculateAttack();
    void calculateDefense();

    void addEquipment(Equipment e);
    List<Equipment> getEquipment();

    void setEquipment(List<Equipment> equipment);

    void setAttack(double attack);

    void setDefense(double defense);

    void setHeight(double height);

    void printCharacter();

    double getEquipmentTotalPerformance();
}
