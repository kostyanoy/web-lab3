const svg = document.getElementById('coordinate_plane');
let xPoint, yPoint;
function changeR(r) {
    let R = document.getElementById("Ry");
    let R2 = document.getElementById("R/2y");
    let R3 = document.getElementById("-R/2y");
    let R4 = document.getElementById("-Ry");
    let R5 = document.getElementById("Rx");
    let R6 = document.getElementById("R/2x");
    let R7 = document.getElementById("-R/2x");
    let R8 = document.getElementById("-Rx");

    if (R) {
        R.textContent = r ? r.toString() : "";
    }
    if (R2) {
        R2.textContent = r ? (r / 2).toString() : "";
    }
    if (R3) {
        R3.textContent = r ? (-r / 2).toString() : "";
    }
    if (R4) {
        R4.textContent = r ? (-r).toString() : "";
    }
    if (R5) {
        R5.textContent = r ? (-r).toString() : "";
    }
    if (R6) {
        R6.textContent = r ? (-r / 2).toString() : "";
    }
    if (R7) {
        R7.textContent = r ? (r / 2).toString() : "";
    }
    if (R8) {
        R8.textContent = r ? (r).toString() : "";
    }
}
document.addEventListener("DOMContentLoaded", () => {
    svg.addEventListener("click", (event) => {
        if (validateR()) {
            const point = getXY(svg, event);
            xPoint = point.x;
            yPoint = point.y;
            const existingPoints = svg.querySelectorAll("circle");
            existingPoints.forEach((point) => {
                svg.removeChild(point);});
            setRound(xPoint, yPoint);
            const tempX = point.x - 200;
            const tempY = 200 - point.y;
            const temp = 120 / r;
            const newX = (tempX / temp).toFixed(1);
            const newY = (tempY / temp).toFixed(1);
            showAlert(`X: ${newX} Y: ${newY}`);
            setTimeout(() => {
                send(newX,newY, r);
            }, 1000);
        }
    });
});
function getXY(svg, event) {
    const rect = svg.getBoundingClientRect();
    return {x: event.clientX - rect.left, y: event.clientY - rect.top};
}
function setRound(x, y) {
    svg.insertAdjacentHTML("beforeend", `<circle r="5" cx="${x}" cy="${y}" fill="brown"></circle>`);
}
function getNewXY(x,y,r) {
    const width = 400;
    const height = 400;
    const centerX = width / 2;
    const centerY = height / 2;
    const cx = centerX + x * (width / (3.3 * r));
    const cy = centerY - y * (height / (3.3 * r));
    setRound(cx, cy);
}

