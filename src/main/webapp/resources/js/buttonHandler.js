document.addEventListener("DOMContentLoaded", function () {
    const resetButton = document.getElementById('resetButton');
    const sendButton = document.getElementById('sendButton');
    resetButton.addEventListener('click', reset);
    sendButton.addEventListener('click', function() {
        if (validateX() && validateY() && validateR()) {
            getNewXY(x, y.value, r);
            send(x,y.value,r);
        } else {
            console.error("Validation failed");
        }
    });
});
function send(x,y,r) {
    console.log("SENDING");
    $.ajax({
        type: "GET",
        url: 'controller',
        data: {
            x: x,
            y: y,
            r: r,
            time: new Date().getTimezoneOffset(),
        },
        success: function (data) {
            window.location.replace('./lastResult.jsp');
        },
        error: function (XHR, status, error) {
            console.error("Error:", error);
        }
    });
}
function reset() {
    $.ajax({
        url: 'clearSessionHistory',
        type: 'GET',
        success: function () {
            $("#sessionData").empty();
            location.reload();
        },
        error: function (xhr, status, error) {
            console.error("Error: ", error);
        }
    });
}
