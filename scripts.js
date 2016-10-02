

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
                    console.log(responseText);

                    if (responseText  == 0) {
                        document.getElementById("raghav").style.visibility = "visible";
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
