

document.addEventListener("DOMContentLoaded", () => {

    console.log("Vehicle Module Loaded Successfully");


    const searchInput = document.getElementById("searchInput");

    if (searchInput) {

        searchInput.addEventListener("keyup", function () {

            const filter = this.value.toLowerCase();

            const rows = document.querySelectorAll("tbody tr");

            rows.forEach(row => {

                const text = row.innerText.toLowerCase();

                if (text.includes(filter)) {

                    row.style.display = "";

                } else {

                    row.style.display = "none";

                }

            });

        });

    }


    const deleteButtons = document.querySelectorAll(".btn-danger");

    deleteButtons.forEach(button => {

        button.addEventListener("click", function (event) {

            const confirmDelete = confirm(
                "Are you sure you want to delete this vehicle?"
            );

            if (!confirmDelete) {

                event.preventDefault();

            }

        });

    });


    const phoneInput = document.querySelector(
        "input[name='driverPhone']"
    );

    if (phoneInput) {

        phoneInput.addEventListener("input", function () {

            this.value = this.value.replace(/\D/g, "");

            if (this.value.length > 10) {

                this.value = this.value.substring(0, 10);

            }

        });

    }


    const vehicleNumber = document.querySelector(
        "input[name='vehicleNumber']"
    );

    if (vehicleNumber) {

        vehicleNumber.addEventListener("input", function () {

            this.value = this.value.toUpperCase();

        });

    }


    const capacity = document.querySelector(
        "input[name='capacity']"
    );

    if (capacity) {

        capacity.addEventListener("input", function () {

            if (this.value < 0) {

                this.value = "";

            }

        });

    }


    const successMessage = document.getElementById("successMessage");

    if (successMessage) {

        setTimeout(() => {

            successMessage.style.display = "none";

        }, 3000);

    }

});