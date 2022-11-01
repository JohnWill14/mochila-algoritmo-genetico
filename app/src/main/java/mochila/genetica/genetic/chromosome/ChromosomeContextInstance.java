package mochila.genetica.genetic.chromosome;

public interface ChromosomeContextInstance {
    int[] getValues();
    int[] getWeight();
    int getPositionValue(int position);
    int getPositionWeight(int position);

    int getNumbersOfGenes();
    int getCapacity();
}
