document.addEventListener('DOMContentLoaded', () => {
    const themeToggleBtn = document.getElementById('themeToggleBtn');
    const root = document.documentElement;

    // Load initial theme from localStorage or default to dark
    const currentTheme = localStorage.getItem('theme') || 'dark';
    root.setAttribute('data-theme', currentTheme);
    updateIcon(currentTheme);

    if (themeToggleBtn) {
        themeToggleBtn.addEventListener('click', () => {
            const current = root.getAttribute('data-theme') || 'dark';
            const newTheme = current === 'dark' ? 'light' : 'dark';
            root.setAttribute('data-theme', newTheme);
            localStorage.setItem('theme', newTheme);
            updateIcon(newTheme);
        });
    }

    function updateIcon(theme) {
        if (!themeToggleBtn) return;
        const icon = themeToggleBtn.querySelector('i');
        if (!icon) return;
        if (theme === 'dark') {
            icon.className = 'fa-solid fa-sun';
        } else {
            icon.className = 'fa-solid fa-moon';
        }
    }
});

// Home Page Contact Form Submission Handler
function handleContactSubmit(event) {
    event.preventDefault();
    const successAlert = document.getElementById('contactSuccessAlert');
    const form = document.getElementById('contactForm');
    
    if (successAlert && form) {
        successAlert.classList.remove('d-none');
        form.reset();
        
        // Hide alert after 5 seconds
        setTimeout(() => {
            successAlert.classList.add('d-none');
        }, 5000);
    }
}
