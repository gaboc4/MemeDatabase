import clarifai2.api.*;
import clarifai2.dto.input.*;
import clarifai2.dto.input.image.ClarifaiImage;
import clarifai2.dto.model.ConceptModel;
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

  }

}
