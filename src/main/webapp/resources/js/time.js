document.addEventListener("DOMContentLoaded", function () {
setTime();
setInterval(setTime, 9000)
});

function setTime(){
    let date = new Date();
    let time = document.getElementById('time');
    time.innerHTML = `${date.toLocaleTimeString()}`;
}