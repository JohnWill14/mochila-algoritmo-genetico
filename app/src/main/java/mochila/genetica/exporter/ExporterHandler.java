package mochila.genetica.exporter;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExporterHandler {
    public void writeList(String filePath, String[] header, List<String[]> datas){
        File file = new File(filePath);
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);
            writer.writeNext(header);

            for(String[] data: datas){

                writer.writeNext(data);
            }

            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
