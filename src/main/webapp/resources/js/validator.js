const y = document.getElementById('y');
alerts = document.getElementById('alerts');
const alertDiv = document.createElement('div');
const checkboxes = document.querySelectorAll('input[type="checkbox"]');
let x, r = null;
document.addEventListener("DOMContentLoaded", function () {
    const buttons = document.querySelectorAll(".chooseButtons button");
    buttons.forEach(function (button) {
        button.addEventListener("click", function () {
            if (button.classList.contains("active")) {
                button.classList.remove("active");
                r = null;
            } else {
                buttons.forEach(function (btn) {
                    btn.classList.remove("active");
                });
                button.classList.add("active");
                r = button.value;
            }
            changeR(r);
        });
    });
    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('click', () => {
            if (checkbox.checked) {
                x = checkbox.value;
                checkboxes.forEach(otherCheckbox => {
                    if (otherCheckbox !== checkbox) {
                        otherCheckbox.checked = false;
                    }
                });
            } else {
                x = null;
            }
        });
    });
});
function replaceDot(value) {
    return value.replace(',', '.');
}
const showAlert = message => {
    alertDiv.textContent = message;
    alerts.appendChild(alertDiv);
    setTimeout(() => {
        alertDiv.remove();
    }, 2000);
}
function validateR() {
    if (r === null) {
        showAlert('Выберите значение для R!');
        return false;
    }
    return true;
}
function validateX() {
    if (x === null) {
        showAlert('Выберите значение X!');
        return false;
    }
    return true;
}
function validateY() {
    let yRep = replaceDot(y.value);
    if (yRep === "") {
        showAlert('Выберите значение для Y!');
        return false;
    }
    if (isNaN(yRep)) {
        showAlert('Значение Y должно быть числом!');
        return false;
    }
    if (yRep.includes('.') && yRep.split('.')[1].length > 2) {
        showAlert('Число Y должно быть не более двух знаков после запятой!');
        return false;
    }
    if (yRep < -3 || yRep > 5) {
        showAlert('Значение Y должно быть в диапазоне от -3 до 5');
        return false;
    }
    return true;
}

