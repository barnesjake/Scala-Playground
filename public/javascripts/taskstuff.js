// alert("I am an alert for testing!");



function deleteTask(id) {
    var req = new XMLHttpRequest();
    req.open("delete", "/tasks-remove/" + id);
    req.onload = function (e) {
        if (req.status = 200) {
            document.location.reload(true);
        }
    };
    req.send();
}

function test() {
    document.getElementsByTagName("button").addEventListener("click", console.log("Testy test"));

}