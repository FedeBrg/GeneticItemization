import classes.Archer;
import implementations.crossovers.SinglePointCrossover;
import implementations.crossovers.TwoPointCrossover;
import implementations.crossovers.UniformCrossover;
import implementations.mutations.IndividualGenMutation;
import implementations.mutations.MultiGenMutation;
import implementations.mutations.UniformMultiGenMutation;
import implementations.selectors.EliteSelection;
import interfaces.*;
import implementations.selectors.RouletteSelection;
import implementations.selectors.UniversalSelection;
import interfaces.Class;
import interfaces.RoleGame;
import interfaces.Selector;
import interfaces.Character;
import interfaces.Class;
import utilities.Parser;
import equipment.Equipment;
import character.CharacterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RoleGameImpl implements RoleGame {
    private List<Equipment> weapons;
    private List<Equipment> boots;
    private List<Equipment> helmets;
    private List<Equipment> gloves;
    private List<Equipment> chestplates;
    double pm;

    @Override
    public List<Equipment> getWeapons() {
        return weapons;
    }

    @Override
    public List<Equipment> getBoots() {
        return boots;
    }

    @Override
    public List<Equipment> getHelmets() {
        return helmets;
    }

    @Override
    public List<Equipment> getGloves() {
        return gloves;
    }

    @Override
    public List<Equipment> getChestplates() {
        return chestplates;
    }

    @Override
    public double getPm() {
        return pm;
    }

    public RoleGameImpl() {
        Parser p = new Parser();

        weapons = p.parseEquipmentFile("armas.tsv");
        boots = p.parseEquipmentFile("botas.tsv");
        helmets = p.parseEquipmentFile("cascos.tsv");
        gloves = p.parseEquipmentFile("guantes.tsv");
        chestplates = p.parseEquipmentFile("pecheras.tsv");

        pm = 0.3;
    }

    public static void main(String[] args){
        RoleGameImpl rg = new RoleGameImpl();
        Selector s = new UniversalSelection();
        Crossover cross = new SinglePointCrossover();
        List<Character> chars = rg.randomGeneration(new Archer(),10);

        chars.get(0).printCharacter();
        chars.get(1).printCharacter();

        Map.Entry<Character,Character> entry = cross.cross(chars.get(0),chars.get(1));

        entry.getKey().printCharacter();
        entry.getValue().printCharacter();

    }

    private double generateRandomHeight(){
        double lower = 1.3;
        double upper = 2.0;
        return Math.random() * (upper - lower) + lower;
    }

    private List<Character> randomGeneration(Class c, int size){
        List<Character> characters = new ArrayList<>(size);

        Random r = new Random();

        int wmax = weapons.size();
        int bmax = boots.size();
        int hmax = helmets.size();
        int gmax = gloves.size();
        int cmax = chestplates.size();

        for (int i = 0; i < size; i++){
            List<Equipment> equipment = new ArrayList<>(5);
            equipment.add(weapons.get(r.nextInt(wmax)));
            equipment.add(boots.get(r.nextInt(bmax)));
            equipment.add(helmets.get(r.nextInt(hmax)));
            equipment.add(gloves.get(r.nextInt(gmax)));
            equipment.add(chestplates.get(r.nextInt(cmax)));
            characters.add(new CharacterImpl(equipment,c,generateRandomHeight()));

        }

        return characters;
    }
}