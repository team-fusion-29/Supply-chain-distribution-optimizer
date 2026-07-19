document.addEventListener('DOMContentLoaded', () => {
    // 1. Chart Data Extractor
    const vehicleCanvas = document.getElementById('vehicleChart');
    let activeVehicles = 0;
    let serviceVehicles = 0;
    let maintVehicles = 0;

    if (vehicleCanvas) {
        activeVehicles = parseInt(vehicleCanvas.getAttribute('data-active')) || 0;
        serviceVehicles = parseInt(vehicleCanvas.getAttribute('data-service')) || 0;
        maintVehicles = parseInt(vehicleCanvas.getAttribute('data-maintenance')) || 0;
    }

    // Default stats if database is completely empty
    if (activeVehicles === 0 && serviceVehicles === 0 && maintVehicles === 0) {
        activeVehicles = 24;
        serviceVehicles = 5;
        maintVehicles = 2;
    }

    // 2. Setup Chart Colors based on theme
    function getThemeColors() {
        const isLight = document.documentElement.getAttribute('data-theme') === 'light';
        return {
            text: isLight ? '#0f172a' : '#ffffff',
            mutedText: isLight ? '#64748b' : '#94a3b8',
            grid: isLight ? '#e2e8f0' : '#293548'
        };
    }

    let colors = getThemeColors();

    // 3. Initialize Delivery Line Chart
    const deliveryCtx = document.getElementById('deliveryChart');
    let deliveryChart;
    if (deliveryCtx) {
        deliveryChart = new Chart(deliveryCtx, {
            type: 'line',
            data: {
                labels: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun'],
                datasets: [{
                    label: 'Efficiency (%)',
                    data: [94, 96, 98, 97, 99, 99.9],
                    borderColor: '#2563eb',
                    backgroundColor: 'rgba(37, 99, 235, 0.1)',
                    borderWidth: 3,
                    tension: 0.4,
                    fill: true
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        display: false
                    }
                },
                scales: {
                    x: {
                        grid: {
                            color: colors.grid
                        },
                        ticks: {
                            color: colors.mutedText
                        }
                    },
                    y: {
                        min: 90,
                        max: 100,
                        grid: {
                            color: colors.grid
                        },
                        ticks: {
                            color: colors.mutedText
                        }
                    }
                }
            }
        });
    }

    // 4. Initialize Vehicle Doughnut Chart
    const vehicleCtx = document.getElementById('vehicleChart');
    let vehicleChart;
    if (vehicleCtx) {
        vehicleChart = new Chart(vehicleCtx, {
            type: 'doughnut',
            data: {
                labels: ['Active', 'In Service', 'Maintenance'],
                datasets: [{
                    data: [activeVehicles, serviceVehicles, maintVehicles],
                    backgroundColor: ['#22c55e', '#2563eb', '#f59e0b'],
                    borderWidth: 2,
                    borderColor: 'rgba(255,255,255,0.05)'
                }]
            },
            options: {
                responsive: true,
                maintainAspectRatio: false,
                plugins: {
                    legend: {
                        position: 'bottom',
                        labels: {
                            color: colors.text,
                            font: {
                                family: "'Inter', sans-serif"
                            }
                        }
                    }
                }
            }
        });
    }

    // 5. Watch theme toggle changes via MutationObserver
    const observer = new MutationObserver(() => {
        colors = getThemeColors();
        
        // Update Line Chart
        if (deliveryChart) {
            deliveryChart.options.scales.x.grid.color = colors.grid;
            deliveryChart.options.scales.x.ticks.color = colors.mutedText;
            deliveryChart.options.scales.y.grid.color = colors.grid;
            deliveryChart.options.scales.y.ticks.color = colors.mutedText;
            deliveryChart.update();
        }

        // Update Doughnut Chart
        if (vehicleChart) {
            vehicleChart.options.plugins.legend.labels.color = colors.text;
            vehicleChart.update();
        }
    });

    observer.observe(document.documentElement, {
        attributes: true,
        attributeFilter: ['data-theme']
    });

    // 6. Search filtering for statistics/logs on dashboard
    const adminSearchInput = document.getElementById('adminSearchInput');
    if (adminSearchInput) {
        adminSearchInput.addEventListener('keyup', function() {
            const filter = this.value.toLowerCase();
            const rows = document.querySelectorAll('table tbody tr');
            rows.forEach(row => {
                const text = row.textContent.toLowerCase();
                row.style.display = text.includes(filter) ? '' : 'none';
            });
        });
    }
});
