import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.input.image.ClarifaiImage;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import com.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by trentololz on 10/1/16.
 */
//public class MemeModel  {
//
//  public static void main(String[] args) {
//
////    final ClarifaiClient client = new ClarifaiBuilder("tjkNjdQ0dxWlRYHn_ncmUpEPII67urr1MNP0Jd6h", "Ewzwtz2bLXD3mqbBFK0V5bNN23NtoClx8Ov9YMOn").buildSync();
//    final ClarifaiClient client = new ClarifaiBuilder(
//            "tjkNjdQ0dxWlRYHn_ncmUpEPII67urr1MNP0Jd6h",
//            "Ewzwtz2bLXD3mqbBFK0V5bNN23NtoClx8Ov9YMOn").buildSync();
//
//    CSVReader reader = null;
//
//    try {
//      reader = new CSVReader(new FileReader("result.csv"));
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    }
//
//    String [] nextLine;
//
//    String url = "jdbc:mysql://104.236.62.179:80/images";
//    try {
//      Connection conn = DriverManager.getConnection(url,
//              "root",
//              "2d214d83d51c0c7a5e220616bb0b10b4ab0afdf49d70cad0");
//
//      Statement statement = conn.createStatement();
//
//      try {
//
//        final List<ClarifaiOutput<Concept>> predictionResults =
//                client.getDefaultModels().generalModel() // You can also do client.getModelByID("id") to get custom models
//                        .predict()
//                        .withInputs(
//                                ClarifaiInput.forImage(ClarifaiImage.of("http://imgur.com/vHHgJ77.jpg"))
//                        )
//                        .executeSync()
//                        .get();
//
//        String[] tags = {predictionResults.get(0).data().get(0).name(),
//                predictionResults.get(0).data().get(1).name(),
//                predictionResults.get(0).data().get(2).name(),
//                predictionResults.get(0).data().get(3).name(),
//                predictionResults.get(0).data().get(4).name()};
//
////        while ((nextLine = reader.readNext()) != null) {
////          //String[] tags = predictionResults(client, nextLine[1]);
//
//          statement.executeUpdate("INSERT INTO images " +
//                  "VALUES (http://imgur.com/vHHgJ77.jpg, 1681, tags[0], tags[1], tags[2], tags[3], tags[4])");
//
//        //}
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//
//
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//
//
//  }
//
//  public static String[] predictionResults(ClarifaiClient client, String url) {
//    final List<ClarifaiOutput<Concept>> predictionResults =
//            client.getDefaultModels().generalModel() // You can also do client.getModelByID("id") to get custom models
//                    .predict()
//                    .withInputs(
//                            ClarifaiInput.forImage(ClarifaiImage.of(url))
//                    )
//                    .executeSync()
//                    .get();
//
//    String[] results = {predictionResults.get(0).data().get(0).name(),
//            predictionResults.get(0).data().get(1).name(),
//            predictionResults.get(0).data().get(2).name(),
//            predictionResults.get(0).data().get(3).name(),
//            predictionResults.get(0).data().get(4).name()};
//
//    return results;
//  }
//}
//
///**
// * System.out.println(predictionResults.get(0).data().get(x).name());
// */

public class MemeModel  {

  //setup
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://104.236.77.117:3306/images";

  // Database credentials
  static final String USER = "root";
  static final String PASS = "root";

  public static void main(String[] args) {

    final ClarifaiClient client = new ClarifaiBuilder(
            "tjkNjdQ0dxWlRYHn_ncmUpEPII67urr1MNP0Jd6h",
            "Ewzwtz2bLXD3mqbBFK0V5bNN23NtoClx8Ov9YMOn").buildSync();

    Connection conn = null;
    PreparedStatement stmt = null;
    //Statement stmt = null;
    String course_code = null, course_desc = null, course_chair = null;

    try {
      // STEP 2: Register JDBC driver
      Class.forName(JDBC_DRIVER).newInstance();

      // STEP 3: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // STEP 5: Excute query
      //stmt = conn.createStatement();

      CSVReader reader = null;
      String [] nextLine;
      String [] results;

      try {
        reader = new CSVReader(new FileReader("result.csv"));
        while ((nextLine = reader.readNext()) != null) {
          results = predictionResults(client, nextLine[1]);
          String url = nextLine[1];
          String upvoteCount = nextLine[0];
          String tag1 = results[0];
          String tag2 = results[1];
          String tag3 = results[2];
          String tag4 = results[3];
          String tag5 = results[4];

          stmt = conn.prepareStatement("INSERT INTO memes (url, upvoteCount, tag1, tag2, tag3, tag4, tag5) values (?, ?, ?, ?, ?, ?, ?)");
          stmt.setString(1, url);
          stmt.setString(2, upvoteCount);
          stmt.setString(3, tag1);
          stmt.setString(4, tag2);
          stmt.setString(5, tag3);
          stmt.setString(6, tag4);
          stmt.setString(7, tag5);
          stmt.executeUpdate();

//          String sql = "INSERT INTO memes " +
//                  "VALUES (@url, @upvoteCount, @tag1, @tag2, @tag3, @tag4, @tag5)";
//          stmt.executeUpdate(sql);
        }
      }
      catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    }
    catch(SQLException se) {
      se.printStackTrace();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    finally {
      try {
        if(stmt != null)
          conn.close();
      }
      catch(SQLException se) {
      }
      try {
        if(conn != null)
          conn.close();
      }
      catch(SQLException se) {
        se.printStackTrace();
      }
    }
    System.out.println("Thank you for your patronage!");

  }

  public static String[] predictionResults(ClarifaiClient client, String url) {
    final List<ClarifaiOutput<Concept>> predictionResults =
            client.getDefaultModels().generalModel() // You can also do client.getModelByID("id") to get custom models
                    .predict()
                    .withInputs(
                            ClarifaiInput.forImage(ClarifaiImage.of(url))
                    )
                    .executeSync()
                    .get();

    System.out.println(url);

    String[] results = {predictionResults.get(0).data().get(0).name(),
            predictionResults.get(0).data().get(1).name(),
            predictionResults.get(0).data().get(2).name(),
            predictionResults.get(0).data().get(3).name(),
            predictionResults.get(0).data().get(4).name()};

    return results;
  }

}

