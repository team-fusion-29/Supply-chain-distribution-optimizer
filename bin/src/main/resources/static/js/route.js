

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

            const routeCode =
                    document.querySelector("[name='routeCode']");

            const source =
                    document.querySelector("[name='source']");

            const destination =
                    document.querySelector("[name='destination']");

            if (routeCode &&
                    routeCode.value.trim() === "") {

                alert("Route Code is required.");

                routeCode.focus();

                e.preventDefault();

                return;

            }

            if (source &&
                    source.value.trim() === "") {

                alert("Source is required.");

                source.focus();

                e.preventDefault();

                return;

            }

            if (destination &&
                    destination.value.trim() === "") {

                alert("Destination is required.");

                destination.focus();

                e.preventDefault();

                return;

            }

        });

    }

});

const distanceField =
        document.querySelector("[name='distance']");

if (distanceField) {

    distanceField.addEventListener("input", function () {

        if (parseFloat(this.value) < 0) {

            alert("Distance cannot be negative.");

            this.value = "";

            this.focus();

        }

    });

}


const estimatedTimeField =
        document.querySelector("[name='estimatedTime']");

if (estimatedTimeField) {

    estimatedTimeField.addEventListener("input", function () {

        if (parseFloat(this.value) < 0) {

            alert("Estimated Time cannot be negative.");

            this.value = "";

            this.focus();

        }

    });

}


const sourceField =
        document.querySelector("[name='source']");

const destinationField =
        document.querySelector("[name='destination']");

if (sourceField && destinationField) {

    destinationField.addEventListener("blur", function () {

        const source =
                sourceField.value.trim().toLowerCase();

        const destination =
                destinationField.value.trim().toLowerCase();

        if (source !== "" &&
                destination !== "" &&
                source === destination) {

            alert("Source and Destination cannot be the same.");

            destinationField.focus();

        }

    });

}

const textInputs =
        document.querySelectorAll(
                "input[type='text'], textarea"
        );

textInputs.forEach(input => {

    input.addEventListener("blur", function () {

        this.value = this.value.trim();

    });

});

const deleteButtons =
        document.querySelectorAll(
                "a[href*='/routes/delete/']"
        );

deleteButtons.forEach(button => {

    button.addEventListener("click", function (e) {

        const confirmDelete = confirm(
                "Are you sure you want to delete this route?"
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
