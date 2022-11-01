package mochila.genetica.genetic.population;

import lombok.*;
import mochila.genetica.genetic.chromosome.Chromosome;
import mochila.genetica.genetic.chromosome.ChromosomeUtil;
import mochila.genetica.genetic.chromosome.ChromosomeContextInstance;
import mochila.genetica.genetic.chromosome.ChromosomeValue;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PopulationInstanceImpl implements PopulationInstance {
    @Getter
    @Setter
    private List<ChromosomeValue> population;
    @Getter
    private ChromosomeContextInstance chromosomeValues;
    @Getter
    @Setter
    @Builder.Default
    private int generationNumber = 0;

    @Override
    public void show() {
        for (ChromosomeValue chromosomeValue : population ){
            System.out.print(chromosomeValue.getChromosome()+" ");
            System.out.print("PESO => "+chromosomeValue.getWeight());
            System.out.println(" Value => "+chromosomeValue.getValue());
        }
    }

    @Override
    public void sortIndividualsByFitnessFunction() {
        Collections.sort(this.population, Comparator.comparingInt(ChromosomeValue::getValue).reversed());
    }
}
