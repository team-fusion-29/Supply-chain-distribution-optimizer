

document.addEventListener("DOMContentLoaded", function () {

    

    const form = document.querySelector("form");

    if (!form) {

        return;

    }

   

    const trackingNumber =
        document.querySelector("[name='trackingNumber']");

    const vehicleNumber =
        document.querySelector("[name='vehicleNumber']");

    const shipmentDate =
        document.querySelector("[name='shipmentDate']");

    const expectedDeliveryDate =
        document.querySelector("[name='expectedDeliveryDate']");

    if (trackingNumber) {

        trackingNumber.addEventListener("input", function () {

            this.value = this.value.toUpperCase();

        });

    }

    

    if (vehicleNumber) {

        vehicleNumber.addEventListener("input", function () {

            this.value = this.value.toUpperCase();

        });

    }
	

	if (vehicleNumber) {

	    vehicleNumber.addEventListener("blur", function () {

	        const vehicleRegex =
	            /^[A-Z]{2}[0-9]{2}[A-Z]{1,2}[0-9]{4}$/;

	        if (this.value.trim() !== "" &&
	            !vehicleRegex.test(this.value)) {

	            alert(
	                "Invalid Vehicle Number.\nExample: AP39AB1234"
	            );

	            this.focus();

	        }

	    });

	}


	if (shipmentDate && expectedDeliveryDate) {

	    expectedDeliveryDate.addEventListener("change", function () {

	        if (shipmentDate.value === "") {

	            alert(
	                "Please select Shipment Date first."
	            );

	            expectedDeliveryDate.value = "";

	            shipmentDate.focus();

	            return;

	        }

	        const shipment =
	            new Date(shipmentDate.value);

	        const delivery =
	            new Date(expectedDeliveryDate.value);

	        if (delivery < shipment) {

	            alert(
	                "Expected Delivery Date cannot be earlier than Shipment Date."
	            );

	            expectedDeliveryDate.value = "";

	            expectedDeliveryDate.focus();

	        }

	    });

	}

	    form.addEventListener("submit", function (event) {

	        // Tracking Number

	        if (trackingNumber &&
	            trackingNumber.value.trim() === "") {

	            alert("Tracking Number is required.");

	            trackingNumber.focus();

	            event.preventDefault();

	            return;

	        }

	        if (vehicleNumber &&
	            vehicleNumber.value.trim() === "") {

	            alert("Vehicle Number is required.");

	            vehicleNumber.focus();

	            event.preventDefault();

	            return;

	        }


	        const confirmation = confirm(
	            "Are you sure you want to save this shipment?"
	        );

	        if (!confirmation) {

	            event.preventDefault();

	            return;

	        }

	    });

	});