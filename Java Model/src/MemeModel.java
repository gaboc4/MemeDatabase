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

<<<<<<< HEAD
 //   client.createModel("memes1").executeSync();


    // Create some concepts
    client.addConcepts()
            .plus(
                    Concept.forID("boscoe")
            )
            .executeSync();

// All concepts need at least one "positive example" (ie, an input whose image file contains that concept)
// So we will add a positive and a negative example of Boscoe
    client.addInputs()
            .plus(
                    ClarifaiInput.forImage(ClarifaiImage.of("https://samples.clarifai.com/puppy.jpeg"))
                            .withConcepts(
                                    Concept.forID("boscoe").withValue(true)
                            ),
                    ClarifaiInput.forImage(ClarifaiImage.of("https://samples.clarifai.com/wedding.jpg"))
                            .withConcepts(
                                    Concept.forID("boscoe").withValue(false)
                            ),
                    ClarifaiInput.forImage(ClarifaiImage.of("http://i.imgur.com/41NvF3q.jpg"))
                            .withConcepts(
                                    Concept.forID("boscoe").withValue(false)
                            )
            )
            .executeSync();


// Now that you have created the boscoe concept, and you have positive
// examples of this concept, you can create a Model that knows this concept
    final ConceptModel petsModel = client.createModel("pets")
            .withOutputInfo(ConceptOutputInfo.forConcepts(
                    Concept.forID("boscoe")
            ))
            .executeSync()
            .get();

// Now that your app contains inputs with the concepts that you wanted to
// detect, you can train your "pets" model
    petsModel.train().executeSync();
=======

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
>>>>>>> e9e949030cd3b956e9b8b6843b60695a754a7b94

  }


}
