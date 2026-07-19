document.addEventListener("DOMContentLoaded", function () {

    initializeTooltips();

    initializeAutoHideAlerts();

    initializeDeleteConfirmation();

    initializeMarkAsReadConfirmation();

    initializeTableSearch();

});

function initializeTooltips() {

    const tooltipTriggerList = [].slice.call(

        document.querySelectorAll('[data-bs-toggle="tooltip"]')

    );

    tooltipTriggerList.map(function (tooltipTriggerEl) {

        return new bootstrap.Tooltip(tooltipTriggerEl);

    });

}

function initializeAutoHideAlerts() {

    const alerts = document.querySelectorAll(".alert-dismissible");

    alerts.forEach(function (alert) {

        setTimeout(function () {

            const bsAlert = bootstrap.Alert.getOrCreateInstance(alert);

            bsAlert.close();

        }, 5000);

    });

}

function initializeDeleteConfirmation() {

    const deleteButtons = document.querySelectorAll(

        ".btn-danger"

    );

    deleteButtons.forEach(function (button) {

        button.addEventListener("click", function (event) {

            const confirmed = confirm(

                "Are you sure you want to delete this notification?"

            );

            if (!confirmed) {

                event.preventDefault();

            }

        });

    });

}

function initializeMarkAsReadConfirmation() {

    const readButtons = document.querySelectorAll(

        ".btn-success"

    );

    readButtons.forEach(function (button) {

        button.addEventListener("click", function (event) {

            const confirmed = confirm(

                "Mark this notification as read?"

            );

            if (!confirmed) {

                event.preventDefault();

            }

        });

    });

}

function initializeTableSearch() {

    const searchInput = document.getElementById("searchInput");

    if (!searchInput) {

        return;

    }

    searchInput.addEventListener("keyup", function () {

        const value = this.value.toLowerCase();

        const rows = document.querySelectorAll(

            "table tbody tr"

        );

        rows.forEach(function (row) {

            const text = row.textContent.toLowerCase();

            row.style.display =

                text.indexOf(value) > -1

                    ? ""

                    : "none";

        });

    });

}
document.addEventListener("DOMContentLoaded", function () {

    initializeFormValidation();

    initializeCharacterCounter();

    initializePriorityBadgeColors();

    initializeFilterDropdown();

    initializeRefreshButton();

    initializeEmptyTableHandling();

});

function initializeFormValidation() {

    const form = document.querySelector("form");

    if (!form) {

        return;

    }

    form.addEventListener("submit", function (event) {

        if (!form.checkValidity()) {

            event.preventDefault();

            event.stopPropagation();

        }

        form.classList.add("was-validated");

    });

}

function initializeCharacterCounter() {

    const messageField = document.getElementById("message");

    const counter = document.getElementById("messageCounter");

    if (!messageField || !counter) {

        return;

    }

    const updateCounter = function () {

        counter.textContent =

            messageField.value.length + " Characters";

    };

    updateCounter();

    messageField.addEventListener("input", updateCounter);

}

function initializePriorityBadgeColors() {

    const badges = document.querySelectorAll(

        ".priority-badge"

    );

    badges.forEach(function (badge) {

        const priority = badge.textContent.trim().toUpperCase();

        badge.classList.remove(

            "bg-secondary",

            "bg-success",

            "bg-warning",

            "bg-danger"

        );

        if (priority === "LOW") {

            badge.classList.add("bg-success");

        } else if (priority === "MEDIUM") {

            badge.classList.add("bg-warning");

        } else if (priority === "HIGH") {

            badge.classList.add("bg-danger");

        } else {

            badge.classList.add("bg-secondary");

        }

    });

}

function initializeFilterDropdown() {

    const filter = document.getElementById("statusFilter");

    if (!filter) {

        return;

    }

    filter.addEventListener("change", function () {

        const value = this.value.toLowerCase();

        const rows = document.querySelectorAll(

            "table tbody tr"

        );

        rows.forEach(function (row) {

            if (value === "all") {

                row.style.display = "";

                return;

            }

            const rowText = row.textContent.toLowerCase();

            row.style.display =

                rowText.includes(value)

                    ? ""

                    : "none";

        });

    });

}

function initializeRefreshButton() {

    const refreshButton = document.getElementById("refreshBtn");

    if (!refreshButton) {

        return;

    }

    refreshButton.addEventListener("click", function () {

        location.reload();

    });

}

function initializeEmptyTableHandling() {

    const tableBody = document.querySelector("table tbody");

    if (!tableBody) {

        return;

    }

    if (tableBody.querySelectorAll("tr").length === 0) {

        tableBody.innerHTML =

            '<tr><td colspan="12" class="text-center text-muted py-4">No Notifications Found.</td></tr>';

    }

}	function initializeAutoRefresh() {

	    const autoRefresh = document.body.getAttribute("data-auto-refresh");

	    if (autoRefresh === "true") {

	        setInterval(function () {

	            window.location.reload();

	        }, 60000);

	    }

	}

	function initializeCopyNotificationId() {

	    const copyButtons = document.querySelectorAll(".copy-notification-id");

	    copyButtons.forEach(function (button) {

	        button.addEventListener("click", function () {

	            const notificationId = this.getAttribute("data-id");

	            if (!notificationId) {

	                return;

	            }

	            navigator.clipboard.writeText(notificationId).then(function () {

	                alert("Notification ID copied successfully.");

	            });

	        });

	    });

	}

	function initializeButtonLoadingEffect() {

	    const buttons = document.querySelectorAll(

	        ".btn-primary, .btn-success, .btn-warning"

	    );

	    buttons.forEach(function (button) {

	        button.addEventListener("click", function () {

	            if (this.type === "submit") {

	                this.disabled = true;

	                const originalText = this.innerHTML;

	                this.innerHTML =

	                    '<span class="spinner-border spinner-border-sm me-2"></span>Processing...';

	                setTimeout(() => {

	                    this.disabled = false;

	                    this.innerHTML = originalText;

	                }, 1500);

	            }

	        });

	    });

	}

	function initializeScrollToTop() {

	    const scrollButton = document.getElementById("scrollTopBtn");

	    if (!scrollButton) {

	        return;

	    }

	    window.addEventListener("scroll", function () {

	        if (window.scrollY > 250) {

	            scrollButton.style.display = "block";

	        } else {

	            scrollButton.style.display = "none";

	        }

	    });

	    scrollButton.addEventListener("click", function () {

	        window.scrollTo({

	            top: 0,

	            behavior: "smooth"

	        });

	    });

	}

	function initializeFadeInAnimation() {

	    const cards = document.querySelectorAll(

	        ".card, .dashboard-card, .notification-card"

	    );

	    cards.forEach(function (card, index) {

	        card.style.opacity = "0";

	        card.style.animation =

	            "fadeInUp .5s ease forwards";

	        card.style.animationDelay =

	            (index * 0.08) + "s";

	    });

	}

	document.addEventListener("DOMContentLoaded", function () {

	    initializeAutoRefresh();

	    initializeCopyNotificationId();

	    initializeButtonLoadingEffect();

	    initializeScrollToTop();

	    initializeFadeInAnimation();

	});