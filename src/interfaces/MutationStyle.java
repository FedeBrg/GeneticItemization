package interfaces;

import equipment.Equipment;

public interface MutationStyle {
    Equipment getMutatedEquipment(Equipment e, RoleGame rg, int i);
}
