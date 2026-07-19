

document.addEventListener("DOMContentLoaded", function () {

    initializeSearch();

    initializeStatusFilter();

    initializeDeleteConfirmation();

});


function initializeSearch() {

    const searchInput = document.getElementById("searchDriver");

    if (!searchInput) return;

    searchInput.addEventListener("keyup", function () {

        const filter = this.value.toLowerCase();

        const rows = document.querySelectorAll("#driverTable tbody tr");

        rows.forEach(row => {

            const text = row.innerText.toLowerCase();

            row.style.display = text.includes(filter)

                ? ""

                : "none";

        });

    });

}


function initializeStatusFilter() {

    const statusFilter = document.getElementById("statusFilter");

    if (!statusFilter) return;

    statusFilter.addEventListener("change", function () {

        const selectedStatus = this.value.toLowerCase();

        const rows = document.querySelectorAll("#driverTable tbody tr");

        rows.forEach(row => {

            const rowText = row.innerText.toLowerCase();

            if (selectedStatus === "") {

                row.style.display = "";

            }

            else {

                row.style.display = rowText.includes(selectedStatus)

                    ? ""

                    : "none";

            }

        });

    });

}

function initializeDeleteConfirmation() {

    const deleteButtons = document.querySelectorAll(".delete-btn");

    deleteButtons.forEach(button => {

        button.addEventListener("click", function (event) {

            const confirmDelete = confirm(
                "Are you sure you want to delete this driver?"
            );

            if (!confirmDelete) {

                event.preventDefault();

            }

        });

    });

}

function showSuccessMessage(message) {

    alert(message);

}


function resetDriverForm() {

    const form = document.querySelector("form");

    if (form) {

        form.reset();

    }

}


function autoFocus() {

    const firstInput = document.querySelector("input");

    if (firstInput) {

        firstInput.focus();

    }

}

autoFocus();


function validateDriverForm() {

    const form = document.querySelector("form");

    if (!form) return true;

    const requiredFields = form.querySelectorAll("[required]");

    for (let field of requiredFields) {

        if (field.value.trim() === "") {

            alert(field.previousElementSibling.innerText + " is required.");

            field.focus();

            return false;

        }

    }

    return true;

}

const form = document.querySelector("form");

if (form) {

    form.addEventListener("submit", function (event) {

        if (!validateDriverForm()) {

            event.preventDefault();

        }

    });

}

const tableRows = document.querySelectorAll("#driverTable tbody tr");

tableRows.forEach(row => {

    row.addEventListener("mouseenter", function () {

        this.style.transition = "0.3s";

        this.style.backgroundColor = "#eef5ff";

    });

    row.addEventListener("mouseleave", function () {

        this.style.backgroundColor = "";

    });

});


const cards = document.querySelectorAll(".driver-card, .dashboard-card, .card");

cards.forEach(card => {

    card.addEventListener("mouseenter", function () {

        this.style.transform = "translateY(-5px)";

    });

    card.addEventListener("mouseleave", function () {

        this.style.transform = "translateY(0px)";

    });

});


function showLoading() {

    console.log("Loading...");

}

function hideLoading() {

    console.log("Loading Complete");

}


function loadDriverWeather() {

    /*
        Future Implementation

        1. Get driver's current route
        2. Call Weather API
        3. Display weather status
        4. Show weather alerts
    */

}

function analyzeDriverRisk() {

    /*
        Future Implementation

        Gemini AI

        Input:
            Driver
            Route
            Weather
            Shipment

        Output:
            Risk Level
            Safety Score
            Recommendation
    */

}

function recommendAlternateRoute() {

    /*
        Future Implementation

        Gemini AI will:

        ✔ Analyze Weather
        ✔ Analyze Traffic
        ✔ Analyze Road Blocks
        ✔ Suggest Best Route

    */

}

function notifyDriver() {

    /*
        Future Implementation

        Email

        SMS

        Push Notification

    */

}


console.log("Driver Module Loaded Successfully");