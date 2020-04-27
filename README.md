# Genetic Itemization
## How to run
### Clone into IDE
As simple as that, keep the file structure as is and you should have no problem
### JAR
In the executables folder theres a .jar to run the project, make sure there is a valid properties file named "config.properties" at the same level as the .jar. To execute just run:
`java -jar GeneticItemization.jar`

## Config file options
### characterClass
- 1 - Warrior (default)
- 2 - Archer
- 3 - Support
- 4 - Spy

### crossover
- 1 - Single Point Crossover (default)
- 2 - Two Point Crossover
- 3 - Annular Crossover
- 4 - Uniform Crossover

### mutation
- 1 - Individual Gen Mutation (default)
- 2 - Multigen Mutation
- 3 - Uniform Multigen Mutation
- 4 - Complete Mutation

### mutationStyle
- 0 - Randomized (default)
- 1 - Small Mutation

### selection1 (a% of the generated children will be selected with this method)
- 1 - Elite Selection (default)
- 2 - Roulette Selection
- 3 - Universal Selection
- 4 - Boltzmann Selection
- 5 - Deterministic Tournaments Selection
- 6 - Probabilistic Tournaments Selection
- 7 - Ranking Selection

### selection2 ((1-a)% of the generated children will be selected with this method)
Same as `selection1`

### replacement1 (a% of the next generation will be selected with this method)
- 1 - Elite Selection (default)
- 2 - Roulette Selection
- 3 - Universal Selection
- 4 - Boltzmann Selection
- 5 - Deterministic Tournaments Selection
- 6 - Probabilistic Tournaments Selection
- 7 - Ranking Selection

### selection2 ((1-a)% of the next generation will be selected with this method)
Same as `replacement1`

### implementation
- 1 - Fill All Implementation (default)
- 2 - Fill Parent Implementation

### endCriteria
- 1 - Time Criteria (default)
- 2 - Generation Quantity Criteria
- 3 - Acceptable Solution Criteria
- 4 - Struct Criteria
- 5 - Content Criteria

### a
Double value used in `selection1` and `selection2`

### b
Double value used in `replacement1` and `replacement2`

### populationSize
Integer value which represents the population size

### mutationProbability
Double value which represents the probability of a gen to being mutated

### stopTime
Integer value used in `Time Criteria` to set the finish time

### maxGeneration
Integer value used in `Generation Quantity Criteria` to set the desired quantity of generations

### targetPopulationPerformance
Integer value used in `Acceptable Solution Criteria` to set the total population performance

### generationsNotChanging
Integer value used in `Struct Criteria` and `Content Criteria` to set the quantity of generations which all of them meet a certain condition (i.e. not increasing total population performance more than 1%) before the end criteria finishes the execution 

### tolerance
Double value used in `Struct Criteria` and `Content Criteria` to set maximium diference between some actual generation value and some previous generation value. In `Struct Criteria` this value is the total generation performance, in `Content Criteria` is the best individual performance of the generation.

### initialTemperature
Double value used in `Boltzmann Selection` to set the initial temperature of the system
