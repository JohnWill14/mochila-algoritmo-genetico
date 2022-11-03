package mochila.genetica.exporter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ExporterPopulationCsvHandler {
    public List<String[]> getDatasForFileCSVFromPopulation(List<DataGeneration> datas){
        List<String[]> ans = new ArrayList<>();

        for(DataGeneration data : datas){
            String[] str = new String[6];

            str[0] = Integer.toString(data.getNumberGeneration());
            str[1] = Integer.toString(data.getSize());
            str[2] = String.format("%f", data.getAverage());
            str[3] = Integer.toString(data.getBestFitness());
            str[4] = Integer.toString(data.getBestValue());
            str[5] = Integer.toString(data.getBestWeight());

            ans.add(str);
        }

        return ans;
    }

    public String[] getHeader(){
        return new String[] {
            "Number", "size", "Average fitness", "best fitness", "best value", "best weight"
        };
    }
}
