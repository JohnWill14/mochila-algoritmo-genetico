package mochila.genetica.genetic.population;

import mochila.genetica.genetic.chromosome.ChromosomeValue;

public interface PopulationInstance {
    void show();
    void sortIndividualsByFitnessFunction();
    void crossover();

    int size();

    void applyMortalityRate();

    ChromosomeValue bestIndividual();

    double averagePopulatonByFitness();
}
