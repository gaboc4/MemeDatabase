import com.google.gson.Gson;


import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import clarifai2.api.*;
import clarifai2.dto.input.*;
import clarifai2.dto.input.image.ClarifaiImage;
import clarifai2.dto.model.ConceptModel;
import clarifai2.dto.model.Model;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.model.output_info.ConceptOutputInfo;
import clarifai2.dto.prediction.Concept;


/**
 * Created by trentololz on 10/1/16.
 */
public class MemeModel  {

  public static void main(String[] args) {

//    final ClarifaiClient client = new ClarifaiBuilder("tjkNjdQ0dxWlRYHn_ncmUpEPII67urr1MNP0Jd6h", "Ewzwtz2bLXD3mqbBFK0V5bNN23NtoClx8Ov9YMOn").buildSync();
    final ClarifaiClient client =
             new ClarifaiBuilder("tjkNjdQ0dxWlRYHn_ncmUpEPII67urr1MNP0Jd6h", "Ewzwtz2bLXD3mqbBFK0V5bNN23NtoClx8Ov9YMOn").buildSync();


    CSVReader reader = null;
    try {
      reader = new CSVReader(new FileReader("memes.csv"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    String [] nextLine;
    try {
      while ((nextLine = reader.readNext()) != null) {
        // nextLine[] is an array of values from the line
        System.out.println(nextLine[0] + nextLine[1] + "etc...");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }


    final List<ClarifaiOutput<Concept>> predictionResults =
            client.getDefaultModels().generalModel() // You can also do client.getModelByID("id") to get custom models
                    .predict()
                    .withInputs(
                            ClarifaiInput.forImage(ClarifaiImage.of("http://imgur.com/LaxDs5R.jpg"))
                    )
                    .executeSync()
                    .get();


    System.out.println(predictionResults);




    for (int x=0; x < predictionResults.get(0).data().size(); x++) {

//        System.out.println(predictionResults.get(x).data().get(y).name());
        System.out.println(predictionResults.get(0).data().get(x).name());
    }
    System.out.println(predictionResults);

    System.exit(0);

  }


}
