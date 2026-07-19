

document.addEventListener("DOMContentLoaded", () => {

  

    const passwordInput = document.getElementById("password");
    const togglePassword = document.getElementById("togglePassword");
    const toggleIcon = document.getElementById("toggleIcon");

    if (togglePassword && passwordInput) {

        togglePassword.addEventListener("click", () => {

            if (passwordInput.type === "password") {

                passwordInput.type = "text";

                toggleIcon.classList.remove("fa-eye");
                toggleIcon.classList.add("fa-eye-slash");

            } else {

                passwordInput.type = "password";

                toggleIcon.classList.remove("fa-eye-slash");
                toggleIcon.classList.add("fa-eye");

            }

        });

    }


    const loginForm = document.querySelector(".login-form");

    if (loginForm) {

        loginForm.addEventListener("submit", function () {

            const button = this.querySelector("button[type='submit']");

            button.disabled = true;

            button.innerHTML = `
                <span class="spinner-border spinner-border-sm me-2"></span>
                Signing In...
            `;

        });

    }




    const inputs = document.querySelectorAll(".form-control");

    inputs.forEach(input => {

        input.addEventListener("focus", () => {

            input.parentElement.style.transform = "scale(1.02)";

        });

        input.addEventListener("blur", () => {

            input.parentElement.style.transform = "scale(1)";

        });

    });



    document.addEventListener("keydown", function (event) {

        if (event.key === "Enter") {

            const activeElement = document.activeElement;

            if (activeElement.tagName === "INPUT") {

                loginForm.requestSubmit();

            }

        }

    });

});