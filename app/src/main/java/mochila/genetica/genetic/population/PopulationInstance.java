package mochila.genetica.genetic.population;

public interface PopulationInstance {
    void show();
    void sortIndividualsByFitnessFunction();
    void crossover();

    int size();

    void mataTudoTaOk();
}
