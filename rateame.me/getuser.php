<?php
$data = json_decode(stripslashes($_POST['data']));

$dankness = 0;


$con = mysqli_connect('127.0.0.1','root','root','images', 3306);

if (!$con) {
    die('Could not connect: ' . mysqli_error($con));
}

mysqli_select_db($con,"images");

$counter = 0;


 foreach($data as $d) {
    $sql="SELECT SUM(upvoteCount) FROM memes WHERE tag1 = '".$d."' OR tag2 = '".$d."' OR tag3 = '".$d."' OR tag4 = '".$d."' OR tag5 = '".$d."'";
    
     $result =  mysqli_query($con,$sql);

     $row = mysqli_fetch_array($result);

     $dankness = intval($row['SUM(upvoteCount)']) + $dankness;

     $counter += 1;
   }

    echo $dankness;



mysqli_close($con);


?>
