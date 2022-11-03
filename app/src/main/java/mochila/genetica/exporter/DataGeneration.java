package mochila.genetica.exporter;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class DataGeneration {
    private int numberGeneration;
    private int size;
    private double average;
    private int bestFitness;
    private int bestValue;
    private int bestWeight;

}
