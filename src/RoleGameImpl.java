import classes.Archer;
import implementations.selectors.EliteSelection;
import implementations.selectors.RouletteSelection;
import implementations.selectors.UniversalSelection;
import interfaces.Class;
import interfaces.Selector;
import utilities.Parser;
import equipment.Equipment;
import character.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoleGame {
    private List<Equipment> weapons;
    private List<Equipment> boots;
    private List<Equipment> helmets;
    private List<Equipment> gloves;
    private List<Equipment> chestplates;

    public RoleGame() {
        Parser p = new Parser();

        weapons = p.parseEquipmentFile("armas.tsv");
        boots = p.parseEquipmentFile("botas.tsv");
        helmets = p.parseEquipmentFile("cascos.tsv");
        gloves = p.parseEquipmentFile("guantes.tsv");
        chestplates = p.parseEquipmentFile("pecheras.tsv");
    }

    public static void main(String[] args){
        RoleGame rg = new RoleGame();
        Selector s = new UniversalSelection();

        List<Character> chars = rg.randomGeneration(new Archer(),50);
        System.out.println("INITIAL GENERATION\n");
        for(Character c : chars){
            c.printCharacter();
        }

        System.out.println("FINAL GENERATION");
        for(Character c : s.select(chars, 10)){
            c.printCharacter();
        }
    }

    private double generateRandomHeight(){
        double lower = 1.3;
        double upper = 2.0;
        return Math.random() * (upper - lower) + lower;
    }

    private List<Character> randomGeneration(Class c, int size){
        List<Character> characters = new ArrayList<>(size);
        List<Equipment> equipment = new ArrayList<>(5);

        Random r = new Random();

        int wmax = weapons.size();
        int bmax = boots.size();
        int hmax = helmets.size();
        int gmax = gloves.size();
        int cmax = chestplates.size();

        for (int i = 0; i < size; i++){
            equipment.add(weapons.get(r.nextInt(wmax)));
            equipment.add(boots.get(r.nextInt(bmax)));
            equipment.add(helmets.get(r.nextInt(hmax)));
            equipment.add(gloves.get(r.nextInt(gmax)));
            equipment.add(chestplates.get(r.nextInt(cmax)));
            characters.add(new Character(equipment,c,generateRandomHeight()));

            equipment.clear();
        }

        return characters;
    }


}
