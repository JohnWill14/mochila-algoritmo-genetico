package mochila.genetica.genetic.population;

import lombok.*;
import mochila.genetica.genetic.chromosome.*;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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

    public void crossover(){
        int quantityCrossover = (int) (this.population.size()*0.2d);
        quantityCrossover = quantityCrossover%2 == 0 ? quantityCrossover:quantityCrossover+1;

        Set<ChromosomeValue> teste = new HashSet<>();
        teste.addAll(this.population);

        List<Chromosome> pairsCrossover = new ArrayList<>();

        for (int i =0; i<quantityCrossover && !teste.isEmpty();i++){
            ChromosomeValue roulette = roulette(teste);
            teste.remove(roulette);
            pairsCrossover.add(roulette.getChromosome());
        }

        int positionInArray = 0;
        int quantityMutation = (int) (quantityCrossover*0.3+1);

        while (pairsCrossover.size()-positionInArray>1){
            Chromosome father = pairsCrossover.get(positionInArray);
            positionInArray++;
            Chromosome mother = pairsCrossover.get(positionInArray);
            positionInArray++;

            int locus = ThreadLocalRandom.current().nextInt(0, father.size());

            Chromosome f1 = mother.clone();
            Chromosome f2 = father.clone();

            for(int i=0; i<locus;i++){
                if(father.getActiveGene(i)){
                    f1.activeGeneFromPosition(i);
                }else{
                    f1.desabilityGeneFromPosition(i);
                }
            }

            for(int i=locus; i<father.size(); i++){
                if(mother.getActiveGene(i)){
                    f2.activeGeneFromPosition(i);
                }else{
                    f2.desabilityGeneFromPosition(i);
                }
            }

            if(quantityMutation>0){
                if(ThreadLocalRandom.current().nextBoolean()){
                    int position = ThreadLocalRandom.current().nextInt(0, f1.size());
                    f1.toggleGeneFromPosition(position);
                }else{
                    int position = ThreadLocalRandom.current().nextInt(0, f2.size());
                    f2.toggleGeneFromPosition(position);
                }

                quantityMutation--;
            }

            int wightF1 = ChromosomeUtil.getWeightIndividualReferencesForChromosomeValues(f1, chromosomeValues);

            if(wightF1<= chromosomeValues.getCapacity()){
                int value = ChromosomeUtil.getValueIndividualReferencesForChromosomeValues(f1, chromosomeValues);
                this.population.add(new ChromosomeValueImpl(f1, value, wightF1));
            }


            int wightF2 = ChromosomeUtil.getWeightIndividualReferencesForChromosomeValues(f2, chromosomeValues);

            if(wightF2<= chromosomeValues.getCapacity()){
                int value = ChromosomeUtil.getValueIndividualReferencesForChromosomeValues(f2, chromosomeValues);
                this.population.add(new ChromosomeValueImpl(f2, value, wightF2));
            }

        }

    }

    @Override
    public int size() {
        return this.population.size();
    }

    @Override
    public void applyMortalityRate() {
       int size = this.size();

       while(size > 100){
           population.remove(population.size() - 1);
           size -= 1;
       }

    }

    @Override
    public ChromosomeValue bestIndividual() {
        return this.population.get(0);
    }

    @Override
    public double averagePopulatonByFitness() {
        return this.population.stream()
                .mapToInt(ChromosomeValue::getValue)
                .average()
                .getAsDouble();
    }

    private ChromosomeValue roulette(Set<ChromosomeValue> conjunto){
        int total = conjunto.stream()
                .map(ChromosomeValue::getValue)
                .reduce(Integer::sum)
                .get();

        double doubleRandom = ThreadLocalRandom.current().nextDouble(0, 1);
        Iterator<ChromosomeValue> iterator = conjunto.iterator();
        ChromosomeValue chromosomeValueRandom = iterator.next();
        double accumulatedProbability = chromosomeValueRandom.getValue()/(total*1d);

        for (ChromosomeValue chromosomeValue ; iterator.hasNext(); ){
            chromosomeValue = iterator.next();
            if(Double.compare(doubleRandom, accumulatedProbability)<0){
                break;
            }

            accumulatedProbability += chromosomeValue.getValue()/(total*1d);
            chromosomeValueRandom = chromosomeValue;
        }

        return chromosomeValueRandom;
    }
}
