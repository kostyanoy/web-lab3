function loadHistory() {
    $.ajax({
        url: 'sessionHistory',
        type: 'GET',
        dataType: 'json',
        success: function (data) {
            $("#sessionData").empty();
            $.each(data, function (index, session) {
                const row = "<tr><td>" + session.x + "</td><td>" + session.y + "</td><td>" + session.r + "</td><td>" + session.flag + "</td><td>" + session.time + "</td><td>" + session.scriptTime + "</td></tr>";
                $("#sessionData").append(row);
                getNewXY(session.x, session.y, session.r);
            });
        },
        error: function (xhr, status, error) {
            console.error("Error: " + error);
        }
    });
}
document.addEventListener("DOMContentLoaded", function () {
    loadHistory();
});

