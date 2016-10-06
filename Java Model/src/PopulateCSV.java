import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by JoshHoffman on 10/2/16.
 */
public class PopulateCSV {

    CSVReader reader;

    PopulateCSV(String csv) {
        try {
            reader = new CSVReader(new FileReader(csv));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void makeCSV() {
        try {
            reader = new CSVReader(new FileReader("result.csv"));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
