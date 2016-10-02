

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
    showUser(response.results[0].result.tag.classes.slice(0,5));

};

function handleError(err) {
    console.log('promise error:', err);
};
function showUser(array) {

    var rank1 = 100;
    var rank2 = 250;
    var rank3 = 600;
    var rank4 = 1000;
    var rank5 = 3000;


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

                     if (responseText > rank5) {
                         swal({title: "Wow!", text: "5/7 meme! <br> Your dankess rating is:  " + responseText, html: true});
                     }
                     else if (responseText > rank4) {
                         swal({title: "Wow!", text: "4/7 meme! <br> Your dankess rating is:  " + responseText, html: true});
                     }
                     else if (responseText > rank3) {
                         swal({title: "Wow!", text: "3/7 meme! <br> Your dankess rating is:  " + responseText, html: true});
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
