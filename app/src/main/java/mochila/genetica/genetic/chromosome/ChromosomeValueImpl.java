package mochila.genetica.genetic.chromosome;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ChromosomeValueImpl implements ChromosomeValue {
    @Getter
    private Chromosome chromosome;
    @Getter
    private int value;
    @Getter
    private int weight;

}
