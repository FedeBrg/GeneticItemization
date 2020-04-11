import classes.Archer;
import implementations.criterias.GenerationQuantityCriteria;
import implementations.criterias.TimeCriteria;
import implementations.crossovers.SinglePointCrossover;
import implementations.mutations.IndividualGenMutation;
import implementations.mutations.MultiGenMutation;
import implementations.selectors.BoltzmannSelection;
import implementations.selectors.EliteSelection;
import implementations.selectors.ProbabilisticTournamentSelection;
import implementations.selectors.RouletteSelection;
import interfaces.*;
import interfaces.Class;
import interfaces.RoleGame;
import interfaces.Selector;
import interfaces.Character;
import utilities.Parser;
import equipment.Equipment;
import character.CharacterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RoleGameImpl implements RoleGame {

    /* Listas de items */
    private List<Equipment> weapons;
    private List<Equipment> boots;
    private List<Equipment> helmets;
    private List<Equipment> gloves;
    private List<Equipment> chestplates;

    /* Probabilidad */
    double pm;

    /* Atributos necesarios para determinar el corte */
    private long stopTime;
    private int currentGeneration;
    private int maxGeneration;

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

    @Override
    public long getStopTime() {
        return stopTime;
    }

    @Override
    public int getCurrentGeneration() {
        return currentGeneration;
    }

    @Override
    public int getMaxGeneration() {
        return maxGeneration;
    }

    public RoleGameImpl() {
        Parser p = new Parser();

        weapons = p.parseEquipmentFile("armas.tsv");
        boots = p.parseEquipmentFile("botas.tsv");
        helmets = p.parseEquipmentFile("cascos.tsv");
        gloves = p.parseEquipmentFile("guantes.tsv");
        chestplates = p.parseEquipmentFile("pecheras.tsv");

        pm = 0.3;

        stopTime = 5000;
        currentGeneration = 0;
        maxGeneration = 15;
    }

    public static void main(String[] args){

        /* Iniciamos todo lo que necesitamos */
        RoleGameImpl rg = new RoleGameImpl();
        Selector selectorMethod = new RouletteSelection();
        Crossover crossoverMethod = new SinglePointCrossover();
        Mutation mutationMethod = new IndividualGenMutation();
        Criteria criteriaMethod = new GenerationQuantityCriteria();
        int populationSize = 10, i, j;
        List<Character> currentPopulation = rg.randomGeneration(new Archer(),populationSize);
        List<Character> recombinedPopulation;
        Map.Entry<Character, Character> recombinedCharacters;
        boolean stopCondition = false;

        /* Iniciamos el criterio de corte (solo por si es necesario) */
        criteriaMethod.start();

        /* Se haran las iteraciones necesarias segun el criterio de corte */
        while(!stopCondition){
            recombinedPopulation = new ArrayList<>();

            /* Se recombinan los padres y se agregan sus hijos a la poblacion */
            for(int k = 0; k < populationSize; k+=2){
                i = rg.generateRandomIndex(populationSize);
                j = rg.generateRandomIndex(populationSize);
                recombinedCharacters = crossoverMethod.cross(currentPopulation.get(i), currentPopulation.get(j));
                recombinedPopulation.add(recombinedCharacters.getValue());
                recombinedPopulation.add(recombinedCharacters.getKey());
            }

            /* Luego se mutan los genes en los hijos y se los agrega a la poblacion */
            for(Character c : recombinedPopulation){
                currentPopulation.add(mutationMethod.mutate(c,rg));
            }

            /* Ahora que tenemos una poblacion de tamaño 2 * K debemos seleccionar los K mas aptos */
            currentPopulation = selectorMethod.select(currentPopulation, populationSize);

            /* Incrementamos el numero de generacion */
            rg.incrementGenerationNumber();

            /* Vemos si ya es hora de cortar */
            stopCondition = criteriaMethod.check(rg);
        }

        System.out.println("TIEMPO CUMPLIDO!\n");
        System.out.printf("GENERACION = %d\n", rg.currentGeneration);
        for(Character c : currentPopulation){
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

    private int generateRandomIndex(int size){
        Random r = new Random();
        return Math.abs(r.nextInt()) % size;
    }

    public void incrementGenerationNumber(){
        this.currentGeneration++;
    }
}