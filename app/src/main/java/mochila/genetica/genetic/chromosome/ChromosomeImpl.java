package mochila.genetica.genetic.chromosome;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class ChromosomeImpl implements Chromosome {

    private int numberOfGenes;
    private boolean[] genes;

    private ChromosomeImpl(int numberOfGenes) {
        this.numberOfGenes = numberOfGenes;
        genes = new boolean[numberOfGenes];
    }

    private ChromosomeImpl(boolean[] genes) {
        this.numberOfGenes = genes.length;
        this.genes = genes.clone();
    }

    public static Chromosome getInstanceFromNumberGenes(int number) {
        return new ChromosomeImpl(number);
    }

    public void activeGeneFromPosition(int position) {
        validPositionInArrayGenes(position);
        genes[position] = true;
    }

    public void desabilityGeneFromPosition(int position) {
        validPositionInArrayGenes(position);
        genes[position] = false;
    }

    public void toggleGeneFromPosition(int position) {
        validPositionInArrayGenes(position);
        genes[position] = !genes[position];
    }

    @Override
    public Chromosome clone() {
        return new ChromosomeImpl(genes);
    }

    @Override
    public boolean getActiveGene(int position) {
        validPositionInArrayGenes(position);
        return genes[position];
    }

    @Override
    public int size() {
        return genes.length;
    }

    private void validPositionInArrayGenes(int position) {
        if (position >= numberOfGenes) {
            throw new IndexOutOfBoundsException("Number of genes is less of the that " + position);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append('[');
        for(boolean b: genes){
            sb.append(b?'1':'0');
        }
        sb.append(']');

        return sb.toString();
    }
}
