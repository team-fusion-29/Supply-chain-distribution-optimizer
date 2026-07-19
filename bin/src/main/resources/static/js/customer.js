document.addEventListener("DOMContentLoaded", () => {

    initializeSearch();

    initializeStatusFilter();

    initializeDeleteConfirmation();

    initializeFormValidation();

});

function initializeSearch() {

    const searchInput = document.getElementById("searchCustomer");

    const table = document.getElementById("customerTable");

    if (!searchInput || !table) return;

    searchInput.addEventListener("keyup", function () {

        const filter = this.value.toLowerCase();

        const rows = table.querySelectorAll("tbody tr");

        rows.forEach(row => {

            const text = row.textContent.toLowerCase();

            row.style.display = text.includes(filter) ? "" : "none";

        });

    });

}

function initializeStatusFilter() {

    const statusFilter = document.getElementById("statusFilter");

    const table = document.getElementById("customerTable");

    if (!statusFilter || !table) return;

    statusFilter.addEventListener("change", function () {

        const selectedStatus = this.value.toLowerCase();

        const rows = table.querySelectorAll("tbody tr");

        rows.forEach(row => {

            if (selectedStatus === "") {

                row.style.display = "";

                return;

            }

            const statusCell = row.cells[6];

            const status = statusCell.textContent.trim().toLowerCase();

            row.style.display = status === selectedStatus ? "" : "none";

        });

    });

}
function initializeDeleteConfirmation() {

    const deleteButtons = document.querySelectorAll(".delete-btn");

    deleteButtons.forEach(button => {

        button.addEventListener("click", function (event) {

            const confirmed = confirm("Are you sure you want to delete this customer?");

            if (!confirmed) {

                event.preventDefault();

            }

        });

    });

}

function initializeFormValidation() {

    const form = document.querySelector("form");

    if (!form) return;

    form.addEventListener("submit", function (event) {

        if (!form.checkValidity()) {

            event.preventDefault();

            event.stopPropagation();

        }

        form.classList.add("was-validated");

    });

}

function focusFirstInput() {

    const firstInput = document.querySelector("input, select, textarea");

    if (firstInput) {

        firstInput.focus();

    }

}

focusFirstInput();

function resetCustomerForm() {

    const form = document.querySelector("form");

    if (form) {

        form.reset();

        form.classList.remove("was-validated");

    }

}
const tableRows = document.querySelectorAll("#customerTable tbody tr");

tableRows.forEach(row => {

    row.addEventListener("mouseenter", function () {

        this.style.transition = "0.3s";

        this.style.backgroundColor = "#eef5ff";

    });

    row.addEventListener("mouseleave", function () {

        this.style.backgroundColor = "";

    });

});

const cards = document.querySelectorAll(".dashboard-card, .card");

cards.forEach(card => {

    card.addEventListener("mouseenter", function () {

        this.style.transform = "translateY(-5px)";

    });

    card.addEventListener("mouseleave", function () {

        this.style.transform = "translateY(0)";

    });

});

function showLoading() {

    console.log("Loading...");

}

function hideLoading() {

    console.log("Loading Complete");

}

function animateDashboardCards() {

    const cards = document.querySelectorAll(".dashboard-card h2");

    cards.forEach(card => {

        card.style.opacity = "0";

        setTimeout(() => {

            card.style.transition = "0.5s";

            card.style.opacity = "1";

        }, 300);

    });

}

animateDashboardCards();

function analyzeCustomerRisk() {

}

function generateCustomerInsights() {

}

function predictCustomerDemand() {

}

function recommendShippingPlan() {

}

console.log("Customer Module Loaded Successfully");

// Tracking spinner handler
const trackingForm = document.getElementById('trackingForm');
if (trackingForm) {
    trackingForm.addEventListener('submit', () => {
        const spinner = document.getElementById('trackingSpinner');
        if (spinner) {
            spinner.classList.remove('d-none');
        }
    });
}