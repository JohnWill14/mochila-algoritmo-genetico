package mochila.genetica.genetic.population;

import mochila.genetica.genetic.chromosome.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Population {
    public static PopulationInstance initializePopulationFromChromosomeValuesAndNumberIndividuals(ChromosomeContextInstance chromosomeContextInstance, int number){
        List<ChromosomeValue> populationList = new ArrayList<>();

        for(int i=0; i<number; i++){
            Chromosome chromosomeRandom = generateRandomChromosomeFromChromosomeValues(chromosomeContextInstance);
            int value = ChromosomeUtil.getValueIndividualReferencesForChromosomeValues(chromosomeRandom, chromosomeContextInstance);
            int weight = ChromosomeUtil.getWeightIndividualReferencesForChromosomeValues(chromosomeRandom, chromosomeContextInstance);

            populationList.add(new ChromosomeValueImpl(chromosomeRandom, value, weight));
        }

        return PopulationInstanceImpl.builder()
                .population(populationList)
                .chromosomeValues(chromosomeContextInstance)
                .build();
    }

    private static Chromosome generateRandomChromosomeFromChromosomeValues(ChromosomeContextInstance chromosomeValues){
        int numbersOfGenes = chromosomeValues.getNumbersOfGenes();
        Chromosome chromosomeRandom = ChromosomeImpl.getInstanceFromNumberGenes(numbersOfGenes);

        for(int i=0; i<numbersOfGenes ; i++){
            boolean b = ThreadLocalRandom.current().nextBoolean();

            if(b){
                chromosomeRandom.activeGeneFromPosition(i);
            }else{
                chromosomeRandom.desabilityGeneFromPosition(i);
            }

            int weight = ChromosomeUtil.getWeightIndividualReferencesForChromosomeValues(chromosomeRandom, chromosomeValues);

            if(weight > chromosomeValues.getCapacity()){
                chromosomeRandom.toggleGeneFromPosition(i);
            }

        }

        return  chromosomeRandom;
    }
}
