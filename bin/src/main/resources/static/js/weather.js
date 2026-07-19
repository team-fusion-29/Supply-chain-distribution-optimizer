

document.addEventListener("DOMContentLoaded", () => {

    console.log("Weather Module Loaded");

    initializeSearch();

    initializeDeleteConfirmation();

    initializeFormValidation();

    initializeAnimations();

    autoHideAlerts();

});


function initializeSearch() {

    const searchInput =
        document.getElementById("searchInput");

    if (!searchInput) return;

    searchInput.addEventListener("keyup", function () {

        const filter =
            this.value.toLowerCase();

        const rows =
            document.querySelectorAll(
                "table tbody tr"
            );

        rows.forEach(row => {

            const text =
                row.textContent.toLowerCase();

            row.style.display =
                text.includes(filter)
                    ? ""
                    : "none";

        });

    });

}

function initializeDeleteConfirmation() {

    const deleteButtons =
        document.querySelectorAll(
            "a[href*='/delete/']"
        );

    deleteButtons.forEach(button => {

        button.addEventListener("click", function (e) {

            const confirmDelete =
                confirm(
                    "Are you sure you want to delete this weather record?"
                );

            if (!confirmDelete) {

                e.preventDefault();

            }

        });

    });

}

function initializeFormValidation() {

    const forms =
        document.querySelectorAll("form");

    forms.forEach(form => {

        form.addEventListener("submit", function (e) {

            if (!form.checkValidity()) {

                e.preventDefault();

                e.stopPropagation();

            } else {

                showLoadingButton(form);

            }

            form.classList.add("was-validated");

        });

    });

}


function showLoadingButton(form) {

    const submitButton =
        form.querySelector("button[type='submit']");

    if (!submitButton) return;

    submitButton.disabled = true;

    submitButton.innerHTML =
        '<span class="spinner-border spinner-border-sm me-2"></span>Processing...';

}


function autoHideAlerts() {

    const alerts =
        document.querySelectorAll(".alert");

    alerts.forEach(alert => {

        setTimeout(() => {

            alert.style.transition =
                "opacity .5s ease";

            alert.style.opacity = "0";

            setTimeout(() => {

                alert.remove();

            }, 500);

        }, 4000);

    });

}


const inputs =
    document.querySelectorAll(
        ".form-control, .form-select"
    );

inputs.forEach(input => {

    input.addEventListener("focus", function () {

        this.style.transform = "scale(1.02)";

    });

    input.addEventListener("blur", function () {

        this.style.transform = "scale(1)";

    });

});

function initializeAnimations() {

    const cards =
        document.querySelectorAll(
            ".content-card, .stat-card, table tbody tr"
        );

    cards.forEach((card, index) => {

        card.style.opacity = "0";

        card.style.transform = "translateY(20px)";

        setTimeout(() => {

            card.style.transition =
                "all .5s ease";

            card.style.opacity = "1";

            card.style.transform =
                "translateY(0)";

        }, index * 80);

    });

}


document.querySelectorAll(".stat-card h3")
.forEach(counter => {

    const target =
        parseInt(counter.innerText) || 0;

    let current = 0;

    const increment =
        Math.max(1, Math.ceil(target / 40));

    const timer = setInterval(() => {

        current += increment;

        if (current >= target) {

            counter.innerText = target;

            clearInterval(timer);

        } else {

            counter.innerText = current;

        }

    }, 25);

});


function scrollToTop() {

    window.scrollTo({

        top: 0,

        behavior: "smooth"

    });

}


function refreshWeatherPage() {

    location.reload();

}


function showToast(message) {

    console.log("Weather Module : " + message);

}
