package mochila.genetica.genetic.chromosome;

public class ChromosomeUtil {
    public static int getValueIndividualReferencesForChromosomeValues(Chromosome chromosome, ChromosomeContextInstance chromosomeValues) {
        int value = 0;

        for(int i=0; i< chromosomeValues.getNumbersOfGenes(); i++){
            if(chromosome.getActiveGene(i)){
                value+= chromosomeValues.getPositionValue(i);
            }
        }
        return value;
    }

    public static int getWeightIndividualReferencesForChromosomeValues(Chromosome chromosome, ChromosomeContextInstance chromosomeValues) {
        int value = 0;

        for(int i=0; i< chromosomeValues.getNumbersOfGenes(); i++){
            if(chromosome.getActiveGene(i)){
                value+= chromosomeValues.getPositionWeight(i);
            }
        }
        return value;
    }
}
