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

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class RoleGameImpl implements RoleGame {

    /* Listas de items */
    private List<Equipment> weapons;
    private List<Equipment> boots;
    private List<Equipment> helmets;
    private List<Equipment> gloves;
    private List<Equipment> chestplates;

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

    public RoleGameImpl() {
        Parser p = new Parser();

        /* Items */
        weapons = p.parseEquipmentFile("armas.tsv");
        boots = p.parseEquipmentFile("botas.tsv");
        helmets = p.parseEquipmentFile("cascos.tsv");
        gloves = p.parseEquipmentFile("guantes.tsv");
        chestplates = p.parseEquipmentFile("pecheras.tsv");

        weaponsTree = new TreeSet<>(weapons);
        bootsTree = new TreeSet<>(boots);
        helmetsTree = new TreeSet<>(helmets);
        glovesTree = new TreeSet<>(gloves);
        chestplatesTree = new TreeSet<>(chestplates);

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

        /* Leemos el archivo de input */
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("config.properties");
            prop.load(fis);

        } catch (IOException e) {
            System.out.println("Can't open config file.\n");
        }

        /* Iniciamos lo que necesitamos*/
        RoleGameImpl rg = new RoleGameImpl();

        Class characterClass = rg.getClass(Integer.parseInt(prop.getProperty("characterClass")));
        Crossover crossoverMethod = rg.getCrossover(Integer.parseInt(prop.getProperty("crossover")));
        Mutation mutationMethod = rg.getMutation(Integer.parseInt(prop.getProperty("mutation")));
        MutationStyle mutationStyle = rg.getMutationStyle(Integer.parseInt(prop.getProperty("mutationStyle")));

        Selector selectorMethod1 = rg.getSelection(Integer.parseInt(prop.getProperty("selection1")));
        Selector selectorMethod2 = rg.getSelection(Integer.parseInt(prop.getProperty("selection2")));

        Selector replacementMethod1 = rg.getSelection(Integer.parseInt(prop.getProperty("replacement1")));
        Selector replacementMethod2 = rg.getSelection(Integer.parseInt(prop.getProperty("replacement2")));

        Implementation implementationMethod = rg.getImplementation(Integer.parseInt(prop.getProperty("implementation")));

        Criteria criteriaMethod = rg.getCriteria(Integer.parseInt(prop.getProperty("endCriteria")));

        if(criteriaMethod.getClass() == TimeCriteria.class){
            rg.stopTime = Integer.parseInt(prop.getProperty("stopTime"));
        }
        else if(criteriaMethod.getClass() == GenerationQuantityCriteria.class){
            rg.currentGeneration = 0;
            rg.currentGeneration = Integer.parseInt(prop.getProperty("maxGeneration"));
        }
        else if(criteriaMethod.getClass() == AcceptableSolutionCriteria.class){
            rg.targetPopulationPerformance = Integer.parseInt(prop.getProperty("targetPopulationPerformance"));
        }
        else if(criteriaMethod.getClass() == StructCriteria.class){
            rg.currentGenerationPerformance = 0;
            rg.generationsNotChanging = Integer.parseInt(prop.getProperty("generationsNotChanging"));
            rg.tolerance = Integer.parseInt(prop.getProperty("tolerance"));
        }
        else if(criteriaMethod.getClass() == ContentCriteria.class){
            rg.bestPerformance = 0;
            rg.generationsNotChanging = Integer.parseInt(prop.getProperty("generationsNotChanging"));
            rg.tolerance = Integer.parseInt(prop.getProperty("tolerance"));
        }

        rg.a = Double.parseDouble(prop.getProperty("a"));
        rg.b = Double.parseDouble(prop.getProperty("b"));
        rg.pm = Double.parseDouble(prop.getProperty("mutationProbability"));

        int populationSize = Integer.parseInt(prop.getProperty("populationSize"));
        List<Character> currentPopulation = rg.randomGeneration(characterClass,populationSize);
        List<Character> recombinedPopulation;
        List<Character> mutatedPopulation;
        List<Character> betterPerformanceChildren;
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
            mutatedPopulation = new ArrayList<>();

            /* Se recombinan los padres y se agregan sus hijos a la poblacion */
            Collections.shuffle(currentPopulation);
            for(int k = 0; k < populationSize - 1; k += 2){
                recombinedCharacters = crossoverMethod.cross(currentPopulation.get(k), currentPopulation.get(k+1));
                recombinedPopulation.add(recombinedCharacters.getValue());
                recombinedPopulation.add(recombinedCharacters.getKey());
            }

            /* Si size es impar el ultimo se agrega */
            if(populationSize % 2 != 0){
                recombinedPopulation.add(currentPopulation.get(populationSize-1));
            }

            /* Luego se mutan los genes en los hijos y se los agrega a la poblacion */
            for(Character c : recombinedPopulation){
                mutatedPopulation.add(mutationMethod.mutate(c, rg, mutationStyle));
            }

            /* Ahora que tenemos una poblacion de tamaño 2 * K debemos seleccionar los K mas aptos */
            betterPerformanceChildren = selectorMethod1.select(mutatedPopulation,(int) Math.ceil(populationSize*rg.a));
            betterPerformanceChildren.addAll(selectorMethod2.select(mutatedPopulation,(int) Math.floor(populationSize*(1-rg.a))));

            /* Incrementamos el numero de generacion */
            rg.incrementGenerationNumber();

            /* Seleccionamos la siguiente generacion */
            currentPopulation = implementationMethod.selectNextGeneration(currentPopulation, betterPerformanceChildren, populationSize);

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

    private Selector getSelection(int arg){
        switch (arg){
            case 1:
                return new EliteSelection();

            case 2:
                return new RouletteSelection();

            case 3:
                return new UniversalSelection();

            case 4:
                return new BoltzmannSelection();

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


}