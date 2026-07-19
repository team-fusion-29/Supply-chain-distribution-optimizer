document.addEventListener('DOMContentLoaded', () => {
    // 1. Search Table Filter
    const searchInput = document.querySelector('.topbar input');
    const table = document.getElementById('driverShipmentsTable');
    if (searchInput && table) {
        searchInput.addEventListener('keyup', function() {
            const filter = this.value.toLowerCase();
            const rows = table.querySelectorAll('tbody tr');
            rows.forEach(row => {
                const text = row.textContent.toLowerCase();
                row.style.display = text.includes(filter) ? '' : 'none';
            });
        });
    }

    // 2. Form submission spinner
    const statusForm = document.getElementById('statusUpdateForm');
    const spinner = document.getElementById('statusSpinner');
    if (statusForm && spinner) {
        statusForm.addEventListener('submit', () => {
            spinner.classList.remove('d-none');
        });
    }
});
