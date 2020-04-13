import classes.Archer;
import implementations.criterias.*;
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

import java.util.*;

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
    private int maxGeneration;
    private int currentGeneration;
    private int generationsNotChanging;
    private double tolerance;
    private double currentGenerationPerformance;
    private double bestPerformance;
    private double targetPopulationPerformance;

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
    public void setCurrentGenerationPerformance(double currentGenerationPerformance) {
        this.currentGenerationPerformance = currentGenerationPerformance;
    }

    @Override
    public int getGenerationsNotChanging() {
        return generationsNotChanging;
    }

    @Override
    public int getMaxGeneration() {
        return maxGeneration;
    }

    @Override
    public double getTolerance() {
        return tolerance;
    }

    @Override
    public double getCurrentGenerationPerformance() {
        return currentGenerationPerformance;
    }

    @Override
    public double getBestPerformance() {
        return bestPerformance;
    }

    @Override
    public void setBestPerformance(double bestPerformance) {
        this.bestPerformance = bestPerformance;
    }

    @Override
    public double getTargetPopulationPerformance() {
        return targetPopulationPerformance;
    }

    public RoleGameImpl() {
        Parser p = new Parser();

        /* Items */
        weapons = p.parseEquipmentFile("armas.tsv");
        boots = p.parseEquipmentFile("botas.tsv");
        helmets = p.parseEquipmentFile("cascos.tsv");
        gloves = p.parseEquipmentFile("guantes.tsv");
        chestplates = p.parseEquipmentFile("pecheras.tsv");

        /* Probability */
        pm = 0.3;

        /* Time criteria */
        stopTime = 5000;

        /* Generations criteria */
        currentGeneration = 0;
        maxGeneration = 15;

        /* Struct criteria */
        currentGenerationPerformance = 0;

        /* Content criteria */
        bestPerformance = 0;

        /* Acceptable solution criteria */
        targetPopulationPerformance = 350;

        /* Struct criteria y Content criteria */
        generationsNotChanging = 5;
        tolerance = 2;
    }

    public static void main(String[] args){

        /* Iniciamos lo que necesitamos*/
        RoleGameImpl rg = new RoleGameImpl();
        Selector selectorMethod = new RouletteSelection();
        Crossover crossoverMethod = new SinglePointCrossover();
        Mutation mutationMethod = new IndividualGenMutation();
        Criteria criteriaMethod = new AcceptableSolutionCriteria();
        int populationSize = 25, i, j;
        List<Character> currentPopulation = rg.randomGeneration(new Archer(),populationSize);
        List<Character> recombinedPopulation;
        Map.Entry<Character, Character> recombinedCharacters;
        boolean stopCondition = false;

        /* Iniciamos el criterio de corte (solo por si es necesario) */
        rg.setBestPerformance(rg.calculateBestPerformance(currentPopulation));
        rg.setCurrentGenerationPerformance(rg.calculateCurrentGenerationPerformance(currentPopulation));
        criteriaMethod.start();

        rg.printPopulation(currentPopulation, "INITIAL\n");

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

            /* Calculamos la mejor performance */
            rg.setBestPerformance(rg.calculateBestPerformance(currentPopulation));

            /* Seteamos la performance actual (Struct criteria) */
            rg.setCurrentGenerationPerformance(rg.calculateCurrentGenerationPerformance(currentPopulation));

            /* Vemos si ya es hora de cortar */
            stopCondition = criteriaMethod.check(rg);
        }

        rg.printPopulation(currentPopulation, "FINAL\n");
    }

    private double calculateCurrentGenerationPerformance(List<Character> population) {
        double toReturn = 0;

        for(Character c : population){
            toReturn += c.getPerformance();
        }

        return toReturn;
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

    private double calculateBestPerformance(List<Character> population){
        double toReturn = 0;

        for(Character c : population){
            if(c.getPerformance() > toReturn){
                toReturn = c.getPerformance();
            }
        }

        return toReturn;
    }

    private void printPopulation(List<Character> population, String s){
        System.out.println(s);
        for(Character c : population){
            c.printCharacter();
        }
    }
}