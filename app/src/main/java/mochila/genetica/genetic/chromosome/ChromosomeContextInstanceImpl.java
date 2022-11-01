package mochila.genetica.genetic.chromosome;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
public class ChromosomeContextInstanceImpl implements ChromosomeContextInstance {

    @Getter
    private int values[];
    @Getter
    private int weight[];
    @Getter
    private int numbersGenes;
    private int capacity;



    private ChromosomeContextInstanceImpl(int[] values, int[] weight, int capacity) {
        this.values = values.clone();
        this.weight = weight.clone();
        this.capacity = capacity;
        numbersGenes = values.length;
    }

    public static ChromosomeContextInstance fromOfValueAndWeigthAndCapacity(int[] values, int[] weight, int capacity){
        if(values == null || weight == null){
            throw new RuntimeException("Objects values and weight from class"+ ChromosomeContextInstance.class.getName()+" is not must null");
        }
        if(values.length != weight.length){
            throw new RuntimeException("Objects values and weight must have same length");
        }
        return new ChromosomeContextInstanceImpl(values, weight, capacity);
    }

    public int getPositionValue(int position){
        validPositionInBothArrays(position);
        return values[position];
    }

    public int getPositionWeight(int position){
        validPositionInBothArrays(position);
        return weight[position];
    }

    @Override
    public int getNumbersOfGenes() {
        return this.numbersGenes;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    private void validPositionInBothArrays(int position) {
        if (position >= values.length) {
            throw new IndexOutOfBoundsException("position " + position + " invalid");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append('[');
        for(int i = 0; i<values.length; i++){
            sb.append('(');
            sb.append(values[i]);
            sb.append(" ,");
            sb.append(weight[i]);
            sb.append(')');
            sb.append(' ');
        }
        sb.append(']');

        return sb.toString();
    }
}
