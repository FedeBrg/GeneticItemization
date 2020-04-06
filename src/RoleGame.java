import Utilities.Parser;
import equipment.Equipment;

import java.util.List;
import java.util.Random;

public class RoleGame {
    public static void main(String[] args){

        Parser p = new Parser();

        List<Equipment> weapons = p.parseEquipmentFile("armas.tsv");
        List<Equipment> boots = p.parseEquipmentFile("botas.tsv");
        List<Equipment> helmets = p.parseEquipmentFile("cascos.tsv");
        List<Equipment> gloves = p.parseEquipmentFile("guantes.tsv");
        List<Equipment> chestplates = p.parseEquipmentFile("pecheras.tsv");


    }

    private double generateRandomHeight(){
        double lower = 1.3;
        double upper = 2.0;
        return Math.random() * (upper - lower) + lower;
    }
}
