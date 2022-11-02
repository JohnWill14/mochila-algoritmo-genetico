package mochila.genetica.genetic.chromosome;

public interface Chromosome {
    void activeGeneFromPosition(int position);

    void desabilityGeneFromPosition(int position);

    void toggleGeneFromPosition(int position);

    Chromosome clone();
    boolean getActiveGene(int position);

    int size();
}
