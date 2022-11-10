package mochila.genetica.genetic.population;

import mochila.genetica.genetic.chromosome.*;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Population {
    public static PopulationInstance initializePopulationFromChromosomeValuesAndNumberIndividuals(ChromosomeContextInstance chromosomeContextInstance, int number){
        List<ChromosomeValue> populationList = new ArrayList<>();

        List<Pair<Integer, Double>> pesosRelativos = new ArrayList<>();
        for(int i=0; i <chromosomeContextInstance.getNumbersOfGenes(); i++){
            int value = chromosomeContextInstance.getPositionValue(i);
            int weight = chromosomeContextInstance.getPositionWeight(i);
            Pair<Integer, Double> pair = Pair.of(i, value/(weight*1d));

            pesosRelativos.add(pair);
        }

        pesosRelativos = pesosRelativos.stream()
                .sorted(Comparator.comparing(Pair::getValue))
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        Chromosome chromosomeConstruct = ChromosomeImpl.getInstanceFromNumberGenes(chromosomeContextInstance.getNumbersOfGenes());

        int weightObject = 0;
        Iterator<Pair<Integer, Double>> pairIterator = pesosRelativos.iterator();

        while(weightObject <= chromosomeContextInstance.getCapacity() && pairIterator.hasNext()){
            Pair<Integer, Double> integerDoublePair = pairIterator.next();
            if(chromosomeContextInstance.getPositionWeight(integerDoublePair.getLeft())+weightObject<=chromosomeContextInstance.getCapacity()){
                weightObject += chromosomeContextInstance.getPositionWeight(integerDoublePair.getLeft());
                chromosomeConstruct.activeGeneFromPosition(integerDoublePair.getLeft());
            }
        }

        int value = ChromosomeUtil.getValueIndividualReferencesForChromosomeValues(chromosomeConstruct, chromosomeContextInstance);
        int weight = ChromosomeUtil.getWeightIndividualReferencesForChromosomeValues(chromosomeConstruct, chromosomeContextInstance);

        ChromosomeValue chromosomeValue = new ChromosomeValueImpl(chromosomeConstruct, value, weight);

        populationList.add(chromosomeValue);

        for(int i=0;i<number-1;i++){
            Chromosome chromosome = generateRandomChromosomeFromChromosomeValuesAndChromosomeConstruct(chromosomeContextInstance, chromosomeConstruct);
            int valueRandom = ChromosomeUtil.getValueIndividualReferencesForChromosomeValues(chromosome, chromosomeContextInstance);
            int weightRandom = ChromosomeUtil.getWeightIndividualReferencesForChromosomeValues(chromosome, chromosomeContextInstance);

            ChromosomeValue cV = new ChromosomeValueImpl(chromosome, valueRandom, weightRandom);
            populationList.add(cV);
        }

        return PopulationInstanceImpl.builder()
                .population(populationList)
                .chromosomeValues(chromosomeContextInstance)
                .build();
    }

    private static Chromosome generateRandomChromosomeFromChromosomeValues(ChromosomeContextInstance chromosomeContextInstance){
        int numbersOfGenes = chromosomeContextInstance.getNumbersOfGenes();
        Chromosome chromosomeRandom = ChromosomeImpl.getInstanceFromNumberGenes(numbersOfGenes);

        for(int i=0; i<numbersOfGenes ; i++){
            boolean b = ThreadLocalRandom.current().nextBoolean();

            if(b){
                chromosomeRandom.activeGeneFromPosition(i);
            }else{
                chromosomeRandom.desabilityGeneFromPosition(i);
            }

            int weight = ChromosomeUtil.getWeightIndividualReferencesForChromosomeValues(chromosomeRandom, chromosomeContextInstance);

            if(weight > chromosomeContextInstance.getCapacity()){
                chromosomeRandom.toggleGeneFromPosition(i);
            }

        }

        return  chromosomeRandom;
    }

    private static Chromosome generateRandomChromosomeFromChromosomeValuesAndChromosomeConstruct(ChromosomeContextInstance chromosomeContextInstance,
                                                                                                 Chromosome chromosome){
        Chromosome chromosomeRandom;

        chromosomeRandom = chromosome.clone();

        for(int i=0; i<chromosome.size() ; i++){
            boolean b = ThreadLocalRandom.current().nextBoolean();
            if(b){
                chromosomeRandom.activeGeneFromPosition(i);
            }else{
                chromosomeRandom.desabilityGeneFromPosition(i);
            }

            int weight = ChromosomeUtil.getWeightIndividualReferencesForChromosomeValues(chromosomeRandom, chromosomeContextInstance);

            if(weight > chromosomeContextInstance.getCapacity()){
                chromosomeRandom.toggleGeneFromPosition(i);
            }

        }

        return  chromosomeRandom;
    }
}
