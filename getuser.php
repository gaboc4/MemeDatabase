<?php
$data = json_decode(stripslashes($_POST['data']));

$dankness = 0;

 // echo 'executed' . PHP_EOL;

$con = mysqli_connect('127.0.0.1','root','root','images', 3306);

//2d214d83d51c0c7a5e220616bb0b10b4ab0afdf49d70cad0
if (!$con) {
    die('Could not connect: ' . mysqli_error($con));
}

mysqli_select_db($con,"images");


 foreach($data as $d) {
    $sql="SELECT SUM(upvoteCount) FROM memes WHERE tag1 = '".$d."' OR tag2 = '".$d."' OR tag3 = '".$d."' OR tag4 = '".$d."' OR tag5 = '".$d."'";
    //  echo $mysqli_query($con,$sql);
     $result =  mysqli_query($con,$sql);

     $row = mysqli_fetch_array($result);

     $dankness = intval($row['SUM(upvoteCount)']) + $dankness;

    echo $d;
   }

    echo $dankness;



mysqli_close($con);
?>
