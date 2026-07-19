

document.addEventListener("DOMContentLoaded", () => {

    const passwordInput = document.getElementById("password");
    const confirmPasswordInput = document.getElementById("confirmPassword");

    const togglePassword = document.getElementById("togglePassword");
    const toggleIcon = document.getElementById("toggleIcon");

    if (togglePassword && passwordInput) {

        togglePassword.addEventListener("click", () => {

            if (passwordInput.type === "password") {

                passwordInput.type = "text";

                if (confirmPasswordInput) {
                    confirmPasswordInput.type = "text";
                }

                toggleIcon.classList.remove("fa-eye");
                toggleIcon.classList.add("fa-eye-slash");

            } else {

                passwordInput.type = "password";

                if (confirmPasswordInput) {
                    confirmPasswordInput.type = "password";
                }

                toggleIcon.classList.remove("fa-eye-slash");
                toggleIcon.classList.add("fa-eye");

            }

        });

    }


    if (confirmPasswordInput) {

        confirmPasswordInput.addEventListener("keyup", () => {

            if (passwordInput.value === "" || confirmPasswordInput.value === "") {

                confirmPasswordInput.style.borderColor = "";

                return;
            }

            if (passwordInput.value === confirmPasswordInput.value) {

                confirmPasswordInput.style.borderColor = "#22c55e";

            } else {

                confirmPasswordInput.style.borderColor = "#ef4444";

            }

        });

    }

    const inputs = document.querySelectorAll(".form-control, .form-select");

    inputs.forEach(input => {

        input.addEventListener("focus", () => {

            if (input.parentElement) {
                input.parentElement.style.transform = "scale(1.02)";
            }

        });

        input.addEventListener("blur", () => {

            if (input.parentElement) {
                input.parentElement.style.transform = "scale(1)";
            }

        });

    });


    const registerForm = document.querySelector(".register-form");

    if (registerForm) {

        registerForm.addEventListener("submit", function (event) {

            if (passwordInput.value !== confirmPasswordInput.value) {

                event.preventDefault();

                alert("Passwords do not match!");

                return;
            }

            const button = this.querySelector("button[type='submit']");

            button.disabled = true;

            button.innerHTML = `
                <span class="spinner-border spinner-border-sm me-2"></span>
                Creating Account...
            `;

        });

    }

});