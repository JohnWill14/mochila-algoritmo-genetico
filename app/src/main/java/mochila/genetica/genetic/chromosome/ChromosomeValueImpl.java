package mochila.genetica.genetic.chromosome;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ChromosomeValueImpl implements ChromosomeValue {
    @Getter
    private Chromosome chromosome;
    @Getter
    private int value;
    @Getter
    private int weight;

}
