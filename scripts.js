

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
    console.log('promise response:', response);



};

function handleError(err) {
    console.log('promise error:', err);
};
function showUser(str) {
    if (str == "") {
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
                document.getElementById("txtHint").innerHTML = this.responseText;
            }
        };
        xmlhttp.open("GET","getuser.php?q="+str,true);
        xmlhttp.send();
    }
}
