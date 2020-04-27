import classes.Archer;
import classes.Spy;
import classes.Support;
import classes.Warrior;
import implementations.criterias.*;
import implementations.crossovers.AnnularCrossover;
import implementations.crossovers.SinglePointCrossover;
import implementations.crossovers.TwoPointCrossover;
import implementations.crossovers.UniformCrossover;
import implementations.implementation.FillAllImplementation;
import implementations.implementation.FillParentImplementation;
import implementations.mutationStyles.RandomizedMutation;
import implementations.mutationStyles.SmallMutation;
import implementations.mutations.CompleteMutation;
import implementations.mutations.IndividualGenMutation;
import implementations.mutations.MultiGenMutation;
import implementations.mutations.UniformMultiGenMutation;
import implementations.selectors.*;
import interfaces.*;
import interfaces.Class;
import interfaces.RoleGame;
import interfaces.Selector;
import interfaces.Character;
import utilities.Parser;
import equipment.Equipment;
import character.CharacterImpl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RoleGameImplTests implements RoleGame {

    /* Listas de items */
    private final List<Equipment> weapons;
    private final List<Equipment> boots;
    private final List<Equipment> helmets;
    private final List<Equipment> gloves;
    private final List<Equipment> chestplates;

    /* TreeSets para mutation */
    private  TreeSet<Equipment> weaponsTree;
    private  TreeSet<Equipment> bootsTree;
    private  TreeSet<Equipment> helmetsTree;
    private  TreeSet<Equipment> glovesTree;
    private  TreeSet<Equipment> chestplatesTree;

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

    /* Probabilidades de cada metodo de seleccion */
    private double a;
    private double b;

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
    public TreeSet<Equipment> getWeaponsTree() {
        return weaponsTree;
    }

    @Override
    public TreeSet<Equipment> getBootsTree() {
        return bootsTree;
    }

    @Override
    public TreeSet<Equipment> getHelmetsTree() {
        return helmetsTree;
    }

    @Override
    public TreeSet<Equipment> getGlovesTree() {
        return glovesTree;
    }

    @Override
    public TreeSet<Equipment> getChestplatesTree() {
        return chestplatesTree;
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

    public RoleGameImplTests() {
        Parser p = new Parser();

        /* Items */
        weapons = p.parseEquipmentFile("armas.tsv");
        boots = p.parseEquipmentFile("botas.tsv");
        helmets = p.parseEquipmentFile("cascos.tsv");
        gloves = p.parseEquipmentFile("guantes.tsv");
        chestplates = p.parseEquipmentFile("pecheras.tsv");

        weaponsTree = new TreeSet<Equipment>(weapons);
        bootsTree = new TreeSet<Equipment>(boots);
        helmetsTree = new TreeSet<Equipment>(helmets);
        glovesTree = new TreeSet<Equipment>(gloves);
        chestplatesTree = new TreeSet<Equipment>(chestplates);

        /* Probability */
        pm = 0.3;

        /* Time criteria */
        stopTime = 30000;

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

        /* Leemos el archivo de input */
        int characterClassP = 1;
        int crossoverP = 2;
        int mutationP = 1;
        int mutationStyleP = 1;
        int selection1P = 1;
        int selection2P = 1;
        int replacement1P = 1;
        int replacement2P = 1;
        int implementationP = 1;
        int endCriteriaP = 2;
        double aP = 1;
        double bP = 1;
        int populationSizeP = 250;
        double mutationProbabilityP = 0.5;
        int stopTimeP = 15000;
        int maxGenerationP = 500;
        int targetPopulationPerformanceP = 350;
        int generationsNotChangingP = 15;
        double toleranceP = 0.3;
        int initialTemperatureP = 100;


        /* Iniciamos lo que necesitamos*/
        RoleGameImplTests rg = new RoleGameImplTests();

        for(characterClassP = 1; characterClassP<=4;characterClassP++){
            for(crossoverP = 1;crossoverP<=4;crossoverP++){
                for (mutationP = 1; mutationP<=4;mutationP++){
                    for (selection1P = 1;selection1P<=7;selection1P++){
                        for(replacement1P = 1;replacement1P<=7;replacement1P++){
                            for (implementationP = 1;implementationP<=2;implementationP++){
                                for(mutationStyleP = 1;mutationStyleP<=2;mutationStyleP++) {

                                    String filename = String.format("c%dc%dm%ds%dr%di%dms%d", characterClassP, crossoverP, mutationP, selection1P, replacement1P, implementationP,mutationStyleP);
                                    System.out.println(filename);
                                    Class characterClass = rg.getClass(characterClassP);
                                    Crossover crossoverMethod = rg.getCrossover(crossoverP);
                                    Mutation mutationMethod = rg.getMutation(mutationP);
                                    MutationStyle mutationStyle = rg.getMutationStyle(mutationStyleP);

                                    Selector selectorMethod1 = rg.getSelection(selection1P, initialTemperatureP);
                                    Selector selectorMethod2 = rg.getSelection(selection2P, initialTemperatureP);

                                    Selector replacementMethod1 = rg.getSelection(replacement1P, initialTemperatureP);
                                    Selector replacementMethod2 = rg.getSelection(replacement2P, initialTemperatureP);

                                    Implementation implementationMethod = rg.getImplementation(implementationP);

                                    Criteria criteriaMethod = rg.getCriteria(endCriteriaP);

                                    if (criteriaMethod.getClass() == TimeCriteria.class) {
                                        rg.stopTime = stopTimeP;
                                    } else if (criteriaMethod.getClass() == GenerationQuantityCriteria.class) {
                                        rg.currentGeneration = 0;
                                        rg.maxGeneration = maxGenerationP;
                                    } else if (criteriaMethod.getClass() == AcceptableSolutionCriteria.class) {
                                        rg.targetPopulationPerformance = targetPopulationPerformanceP;
                                    } else if (criteriaMethod.getClass() == StructCriteria.class) {
                                        rg.currentGenerationPerformance = 0;
                                        rg.generationsNotChanging = generationsNotChangingP;
                                        rg.tolerance = toleranceP;
                                    } else if (criteriaMethod.getClass() == ContentCriteria.class) {
                                        rg.bestPerformance = 0;
                                        rg.generationsNotChanging = generationsNotChangingP;
                                        rg.tolerance = toleranceP;
                                    }

                                    rg.a = aP;
                                    rg.b = bP;
                                    rg.pm = mutationProbabilityP;

                                    int populationSize = populationSizeP;
                                    List<Character> currentPopulation = rg.randomGeneration(characterClass, populationSize);
                                    List<Character> recombinedPopulation;
                                    List<Character> mutatedPopulation;
                                    List<Character> betterPerformanceChildren;
                                    Map.Entry<Character, Character> recombinedCharacters;
                                    boolean stopCondition = false;
                                    StringBuilder toAppend = new StringBuilder();
                                    int ceilSize = (int) Math.ceil(populationSize * rg.a);

                                    /* Iniciamos el criterio de corte (solo por si es necesario) */
                                    rg.setBestPerformance(rg.calculateBestPerformance(currentPopulation));
                                    rg.setCurrentGenerationPerformance(rg.calculateCurrentGenerationPerformance(currentPopulation));
                                    criteriaMethod.start();


                                    /* Se haran las iteraciones necesarias segun el criterio de corte */
                                    while (!stopCondition) {
                                        toAppend.append(String.format("%d,%f\n", rg.getCurrentGeneration(), rg.getCurrentGenerationPerformance()));
                                        recombinedPopulation = new ArrayList<>();
                                        mutatedPopulation = new ArrayList<>();

                                        /* Se recombinan los padres y se agregan sus hijos a la poblacion */
                                        Collections.shuffle(currentPopulation);
                                        for (int k = 0; k < populationSize - 1; k += 2) {
                                            recombinedCharacters = crossoverMethod.cross(currentPopulation.get(k), currentPopulation.get(k + 1));
                                            recombinedPopulation.add(recombinedCharacters.getValue());
                                            recombinedPopulation.add(recombinedCharacters.getKey());
                                        }

                                        /* Si size es impar el ultimo se agrega */
                                        if (populationSize % 2 != 0) {
                                            recombinedPopulation.add(currentPopulation.get(populationSize - 1));
                                        }

                                        /* Luego se mutan los genes en los hijos y se los agrega a la poblacion */
                                        for (Character c : recombinedPopulation) {
                                            mutatedPopulation.add(mutationMethod.mutate(c, rg, mutationStyle));
                                        }

                                        /* Ahora que tenemos una poblacion de tamaño 2 * K debemos seleccionar los K mas aptos */

                                        betterPerformanceChildren = selectorMethod1.select(mutatedPopulation, ceilSize);
                                        betterPerformanceChildren.addAll(selectorMethod2.select(mutatedPopulation, populationSize - ceilSize));
                                        //rg.printPopulation(betterPerformanceChildren,String.format("*******************************************\nCurrent gen %d\n",rg.currentGeneration));

                                        //rg.printPopulation(betterPerformanceChildren, "###################\n");

                                        /* Incrementamos el numero de generacion */
                                        rg.incrementGenerationNumber();

                                        /* Seleccionamos la siguiente generacion */
                                        currentPopulation = implementationMethod.selectNextGeneration(currentPopulation, betterPerformanceChildren, populationSize, replacementMethod1, replacementMethod2, rg.b);

                                        /* Calculamos la mejor performance */
                                        rg.setBestPerformance(rg.calculateBestPerformance(currentPopulation));

                                        /* Seteamos la performance actual (Struct criteria) */
                                        rg.setCurrentGenerationPerformance(rg.calculateCurrentGenerationPerformance(currentPopulation));

                                        /* Vemos si ya es hora de cortar */
                                        stopCondition = criteriaMethod.check(rg);
                                    }

                                    rg.writeToFile(filename, toAppend);
                                }
                            }
                        }
                    }
                }
            }
        }
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
        return r.nextInt(size);
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
        population.sort(Comparator.comparingDouble(Character::getPerformance));
        for(Character c : population){
            c.printCharacter();
        }
    }

    private Class getClass(int arg){
        switch (arg){
            case 1:
                return new Warrior();

            case 2:
                return new Archer();

            case 3:
                return new Support();

            case 4:
                return new Spy();

            default:
                return new Warrior();
        }

    }

    private Crossover getCrossover(int arg){
        switch (arg){
            case 1:
                return new SinglePointCrossover();

            case 2:
                return new TwoPointCrossover();

            case 3:
                return new AnnularCrossover();

            case 4:
                return new UniformCrossover();

            default:
                return new SinglePointCrossover();
        }

    }

    private Mutation getMutation(int arg){
        switch (arg){
            case 1:
                return new IndividualGenMutation();

            case 2:
                return new MultiGenMutation();

            case 3:
                return new UniformMultiGenMutation();

            case 4:
                return new CompleteMutation();

            default:
                return new IndividualGenMutation();
        }

    }

    private MutationStyle getMutationStyle(int arg){
        switch (arg){
            case 1:
                return new RandomizedMutation();

            case 2:
                return new SmallMutation();

            default:
                return new RandomizedMutation();
        }

    }

    private Selector getSelection(int arg, double initialTemp){
        switch (arg){
            case 1:
                return new EliteSelection();

            case 2:
                return new RouletteSelection();

            case 3:
                return new UniversalSelection();

            case 4:
                return new BoltzmannSelection(initialTemp);

            case 5:
                return new DeterministicTournamentSelection();

            case 6:
                return new ProbabilisticTournamentSelection();

            case 7:
                return new RankingSelection();

            default:
                return new EliteSelection();
        }

    }

    private Implementation getImplementation(int arg){
        switch (arg){
            case 1:
                return new FillAllImplementation();

            case 2:
                return new FillParentImplementation();

            default:
                return new FillAllImplementation();
        }
    }

    private Criteria getCriteria(int arg){
        switch (arg){
            case 1:
                return new TimeCriteria();

            case 2:
                return new GenerationQuantityCriteria();

            case 3:
                return new AcceptableSolutionCriteria();

            case 4:
                return new StructCriteria();

            case 5:
                return new ContentCriteria();

            default:
                return new TimeCriteria();
        }

    }

    private void writeToFile(String filename, StringBuilder toAppend){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filename, true));
            out.write(toAppend.toString());
            out.close();
        }

        catch (IOException ignored) {}
    }

}