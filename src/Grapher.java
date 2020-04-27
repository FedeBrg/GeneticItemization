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
import org.knowm.xchart.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Grapher {

    public static void main(String[] args) throws IOException {
            Grapher g = new Grapher();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter desired Character Class (1-4): ");
            int a = scanner.nextInt();
            System.out.println("Enter desired Crossover (1-4): ");
            int b = scanner.nextInt();
            System.out.println("Enter desired Mutation (1-4): ");
            int c = scanner.nextInt();
            System.out.println("Enter desired Mutation Style (1-2): ");
            int c1 = scanner.nextInt();
            System.out.println("Enter desired Selection (1-7): ");
            int d = scanner.nextInt();
            System.out.println("Enter desired Replacement (1-7): ");
            int e = scanner.nextInt();
            System.out.println("Enter desired Implementation (1-2): ");
            int f = scanner.nextInt();

            System.out.println("Enter variable argument: (class, cross, mut, muts, sel, rep, impl): ");
            String var = scanner.next();
            g.plot(a,b,c,d,e,f,c1,var);


    }

    private void plot(int characterClassP, int crossoverP, int mutationP, int selection1P, int replacement1P, int implementationP,int mutationStyleP, String variable) throws IOException {
        String format;
        int limit;
        List<String> names = new ArrayList<>();
        Map<String,String> map = new HashMap<>();
        map.put("class","Class: "+getClass(characterClassP));
        map.put("cross","Crossover: "+getCrossover(crossoverP));
        map.put("mut","Mutation: "+getMutation(mutationP));
        map.put("sel","Selection: "+getSelection(selection1P));
        map.put("rep","Replacement: "+getSelection(selection1P));
        map.put("impl","Implementation: "+getImplementation(implementationP));
        map.put("muts","Mutation Style: "+getMutationStyle(mutationStyleP));

        map.remove(variable);

        if(variable.equals("class")){
            format = String.format("c%%dc%dm%ds%dr%di%dms%d",crossoverP,mutationP,selection1P,replacement1P,implementationP,mutationStyleP);
            limit = 4;
            for (int i =1;i<=limit;i++){
                names.add(getClass(i).toString());
            }
        }
        else if(variable.equals("cross")){
            format = String.format("c%dc%%dm%ds%dr%di%dms%d",characterClassP,mutationP,selection1P,replacement1P,implementationP,mutationStyleP);
            limit = 4;
            for (int i =1;i<=limit;i++){
                names.add(getCrossover(i).toString());
            }
        }
        else if (variable.equals("mut")){
            format = String.format("c%dc%dm%%ds%dr%di%dms%d",characterClassP,crossoverP,selection1P,replacement1P,implementationP,mutationStyleP);
            limit = 4;
            for (int i =1;i<=limit;i++){
                names.add(getMutation(i).toString());
            }
        }
        else if(variable.equals("sel")){
            format = String.format("c%dc%dm%ds%%dr%di%dms%d",characterClassP,crossoverP,mutationP,replacement1P,implementationP,mutationStyleP);
            limit = 7;
            for (int i =1;i<=limit;i++){
                names.add(getSelection(i).toString());
            }
        }
        else if(variable.equals("rep")){
            format = String.format("c%dc%dm%ds%dr%%di%dms%d",characterClassP,crossoverP,mutationP,selection1P,implementationP,mutationStyleP);
            limit = 7;
            for (int i =1;i<=limit;i++){
                names.add(getSelection(i).toString());
            }
        }
        else if(variable.equals("impl")){
            format = String.format("c%dc%dm%ds%dr%di%%dms%d",characterClassP,crossoverP,mutationP,selection1P,replacement1P,mutationStyleP);
            limit = 2;
            for (int i =1;i<=limit;i++){
                names.add(getImplementation(i).toString());
            }
        }
        else if(variable.equals("muts")){
            format = String.format("c%dc%dm%ds%dr%di%dms%%d",characterClassP,crossoverP,mutationP,selection1P,replacement1P,implementationP);
            limit = 2;
            for (int i =1;i<=limit;i++){
                names.add(getMutationStyle(i).toString());
            }
        }
        else{
            System.out.println("Invalid arg");
            return;
        }

        List<CSVImporter.SeriesData> series = new ArrayList<>();



        for (int i = 1; i<=limit;i++){
            series.add(readCSV(String.format(format,i),names.get(i-1)));
        }

        for (String value : map.values()){
            System.out.println(value);
        }

        XYChartBuilder xyChartBuilder = new XYChartBuilder();
        xyChartBuilder.xAxisTitle("Generation");
        xyChartBuilder.yAxisTitle("Performance");

        XYChart xyChart = new XYChart(xyChartBuilder);


        xyChart.getStyler().setMarkerSize(0);

        for (CSVImporter.SeriesData sd : series){
            xyChart.addSeries(sd.getSeriesName(),sd.getxAxisData(),sd.getyAxisData());
        }

        new SwingWrapper(xyChart).displayChart();

    }


    private CSVImporter.SeriesData readCSV(String filename, String seriesName) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("src/files/" + filename));
        String row;
        List<Number> xdata = new ArrayList<>();
        List<Number> ydata = new ArrayList<>();
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(",");
            xdata.add(Integer.parseInt(data[0]));
            ydata.add(Double.parseDouble(data[1]));
        }
        csvReader.close();

        return new CSVImporter.SeriesData(xdata,ydata,seriesName);
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
                return new BoltzmannSelection(0);

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
