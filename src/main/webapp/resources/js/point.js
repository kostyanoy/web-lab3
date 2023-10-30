const svg = document.getElementById('coordinate_plane');
let xPoint, yPoint;
function changeR(r) {
    const elements = {
        "Ry": r,
        "R/2y": r / 2,
        "-R/2y": -r / 2,
        "-Ry": -r,
        "Rx": -r,
        "R/2x": -r / 2,
        "-R/2x": r / 2,
        "-Rx": r
    };

    for (const id in elements) {
        const element = document.getElementById(id);
        if (element) {
            element.textContent = elements[id] ? elements[id].toString() : "";
        }
    }
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
