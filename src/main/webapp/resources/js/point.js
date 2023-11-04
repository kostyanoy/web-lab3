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


function drawPoint(event) {
    const svg = document.querySelector('svg');
    const point = getXY(svg, event);
    const xPoint = point.x;
    const yPoint = point.y;

    const existingPoints = svg.querySelectorAll("circle");
    existingPoints.forEach((point) => {
        svg.removeChild(point);
    });

    setRound(svg, xPoint, yPoint);
}

function getXY(svg, event) {
    const rect = svg.getBoundingClientRect();
    return { x: event.clientX - rect.left, y: event.clientY - rect.top };
}

function setRound(svg, cx, cy) {
    const circle = document.createElementNS("http://www.w3.org/2000/svg", "circle");
    circle.setAttribute("cx", cx);
    circle.setAttribute("cy", cy);
    circle.setAttribute("r", 5);
    circle.setAttribute("fill", "red");
    svg.appendChild(circle);
}
