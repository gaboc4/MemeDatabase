

function analyze() {

    Clarifai.getTagsByUrl([
        //'https://samples.clarifai.com/wedding.jpg'
        document.getElementById('url').value
    ]).then(
        handleResponse,
        handleError
    );

}
function handleResponse(response) {
    console.log('promise response:', response.results[0].result.tag.classes);
    showUser(response.results[0].result.tag.classes.slice(0,4));

};

function handleError(err) {
    console.log('promise error:', err);
};
function showUser(array) {

    var rank1 = 0;
    var rank2 = 3;
    var rank3 = 5;
    var rank4 = 8;
    var rank5 = 10;

    var max = 84937 * 5;
    var result = 0;


    if (document.getElementById("url").value == "") {
        document.getElementById("txtHint").innerHTML = "";
        return;
    } else {
        if (window.XMLHttpRequest) {
            // code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        } else {
            // code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                console.log(this.responseText);
                document.getElementById("txtHint").innerHTML = this.responseText;
            }
        };

        dataString = array;

        var jsonString = JSON.stringify(dataString);
           $.ajax({
                type: "POST",
                url: "getuser.php",
                data: {data : jsonString},
                cache: false,

                success: function(responseText){

                    result = responseText / max * 100;
                    console.log(result);

                     if (result > rank5) {
                         swal({title: "Amazing!", text: "5/7 meme! ", html: true});
                     }
                     else if (result > rank4) {
                         swal({title: "Wow!", text: "4/7 meme! " , html: true});
                     }
                     else if (result > rank3) {
                         swal({title: "Okay...", text: "3/7 meme! " , html: true});
                     }
                     else if (result > rank2) {
                         swal({title: "Come on!", text: "2/7 meme!" , html: true});
                     }
                     else if (result > rank1) {
                         swal({title: "Lame!", text: "1/7 meme! ", html: true});
                     }


                },

                error: function(){
                    console.log('error');
                }
            });

        // xmlhttp.open("GET","getuser.php?q="+document.getElementById("url").value,true);
        // xmlhttp.send();
    }
}

window.addEventListener('DOMContentLoaded', function() {document.getElementById('analyzeButton').addEventListener('click', analyze, false), false})
