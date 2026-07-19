

document.addEventListener("DOMContentLoaded", function () {

    

    const searchInput = document.getElementById("searchInput");

    if (searchInput) {

        searchInput.addEventListener("keyup", function () {

            const value = this.value.toLowerCase();

            const rows = document.querySelectorAll("tbody tr");

            rows.forEach(row => {

                const text = row.innerText.toLowerCase();

                row.style.display =
                    text.includes(value) ? "" : "none";

            });

        });

    }


    const form = document.querySelector("form");

    if (form) {

        form.addEventListener("submit", function (e) {

            const warehouseCode =
                document.querySelector("[name='warehouseCode']");

            const warehouseName =
                document.querySelector("[name='warehouseName']");

            if (warehouseCode &&
                warehouseCode.value.trim() === "") {

                alert("Warehouse Code is required.");

                warehouseCode.focus();

                e.preventDefault();

                return;

            }

            if (warehouseName &&
                warehouseName.value.trim() === "") {

                alert("Warehouse Name is required.");

                warehouseName.focus();

                e.preventDefault();

                return;

            }

        });

    }

});

const phoneField =
    document.querySelector("[name='phoneNumber']");

if (phoneField) {

    phoneField.addEventListener("input", function () {

        this.value =
            this.value.replace(/[^0-9]/g, "");

        if (this.value.length > 10) {

            this.value =
                this.value.substring(0, 10);

        }

    });

}


const emailField =
    document.querySelector("[name='email']");

if (emailField) {

    emailField.addEventListener("blur", function () {

        const emailPattern =
            /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (this.value.trim() !== "" &&
            !emailPattern.test(this.value)) {

            alert("Please enter a valid email address.");

            this.focus();

        }

    });

}


const totalCapacity =
    document.querySelector("[name='totalCapacity']");

const availableCapacity =
    document.querySelector("[name='availableCapacity']");

if (availableCapacity && totalCapacity) {

    availableCapacity.addEventListener("change", function () {

        const total =
            parseFloat(totalCapacity.value) || 0;

        const available =
            parseFloat(availableCapacity.value) || 0;

        if (available > total) {

            alert(
                "Available Capacity cannot be greater than Total Capacity."
            );

            availableCapacity.value = "";

            availableCapacity.focus();

        }

    });

}


const textInputs =
    document.querySelectorAll(
        "input[type='text'], input[type='email']"
    );

textInputs.forEach(input => {

    input.addEventListener("blur", function () {

        this.value = this.value.trim();

    });

});

const deleteButtons =
    document.querySelectorAll(
        "a[href*='/warehouses/delete/']"
    );

deleteButtons.forEach(button => {

    button.addEventListener("click", function (e) {

        const confirmDelete = confirm(
            "Are you sure you want to delete this warehouse?"
        );

        if (!confirmDelete) {

            e.preventDefault();

        }

    });

});


const resetButton =
    document.querySelector("button[type='reset']");

if (resetButton) {

    resetButton.addEventListener("click", function (e) {

        const confirmReset = confirm(
            "Do you want to clear all entered data?"
        );

        if (!confirmReset) {

            e.preventDefault();

        }

    });

}

const submitButton =
    document.querySelector("button[type='submit']");

if (submitButton) {

    const form =
        document.querySelector("form");

    form.addEventListener("submit", function () {

        submitButton.disabled = true;

        submitButton.innerHTML =
            "<i class='fa-solid fa-spinner fa-spin'></i> Processing...";

    });

}

const firstInput =
    document.querySelector("input");

if (firstInput) {

    firstInput.focus();

}


const contentCard =
    document.querySelector(".content-card");

if (contentCard) {

    contentCard.style.opacity = "0";

    contentCard.style.transform = "translateY(20px)";

    setTimeout(() => {

        contentCard.style.transition =
            "all .5s ease";

        contentCard.style.opacity = "1";

        contentCard.style.transform =
            "translateY(0)";

    }, 100);

}
