import classes.Archer;
import utilities.Parser;
import equipment.Equipment;

import java.util.List;

public class RoleGame {
    public static void main(String[] args){
        Parser p = new Parser();
        RoleGame rg = new RoleGame();

        List<Equipment> weapons = p.parseEquipmentFile("armas.tsv");
        List<Equipment> boots = p.parseEquipmentFile("botas.tsv");
        List<Equipment> helmets = p.parseEquipmentFile("cascos.tsv");
        List<Equipment> gloves = p.parseEquipmentFile("guantes.tsv");
        List<Equipment> chestplates = p.parseEquipmentFile("pecheras.tsv");

        Archer a = new Archer();
        a.setHeight(rg.generateRandomHeight());
        a.addEquipment(weapons.get(0));
        a.addEquipment(boots.get(0));
        a.addEquipment(helmets.get(0));
        a.addEquipment(gloves.get(0));
        a.addEquipment(chestplates.get(0));
        a.calculateAttack();
        a.calculateDefense();

        a.printCharacter();
        System.out.printf("PERF = %f\n", a.getPerformance());
    }

    private double generateRandomHeight(){
        double lower = 1.3;
        double upper = 2.0;
        return Math.random() * (upper - lower) + lower;
    }
}
