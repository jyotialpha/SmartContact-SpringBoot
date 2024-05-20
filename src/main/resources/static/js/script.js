'use strict';

// Helper functions
function isValidEmail(email) {
	const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	return emailRegex.test(email);
}

function isValidPhoneNumber(phoneNumber) {
	return /^\d{10}$/.test(phoneNumber);
}

function validateForm() {
	let isValid = true;
	const form = document.querySelector('form');
	const inputs = form.querySelectorAll('input, textarea');
	const myInput = document.getElementById("password");
	const confirmPasswordInput = document.getElementById("confirmPassword");

	inputs.forEach(input => {
		if (!input.checkValidity()) {
			isValid = false;
			input.classList.add('invalid');
		} else {
			input.classList.remove('invalid');
		}
	});

	if (myInput.value !== confirmPasswordInput.value) {
		isValid = false;
		confirmPasswordInput.classList.add('invalid');
		alert("Passwords do not match!");
	} else {
		confirmPasswordInput.classList.remove('invalid');
	}

	return isValid;
}

document.addEventListener('DOMContentLoaded', function() {
	const myInput = document.getElementById("password");
	const confirmPasswordInput = document.getElementById("confirmPassword");
	const letterIcon = document.getElementById("letter-icon");
	const capital = document.getElementById("capital-icon");
	const number = document.getElementById("number-icon");
	const length = document.getElementById("length-icon");
	const passwordMessage = document.getElementById("password-message");
	const emailInput = document.querySelector('input[type="email"]');
	const phoneNumberInput = document.getElementById('phoneNumberInput');
	const form = document.querySelector('form');

	// Event delegation for form submission
	form.addEventListener('submit', event => {
		if (validateForm()) {
			// Form is valid, submit the form
			form.submit();
		} else {
			// Form is invalid, prevent submission
			event.preventDefault();
		}
	});

	// Password strength validation
	if (myInput) {
		myInput.addEventListener('focus', () => {
			passwordMessage.style.display = "block";
		});

		myInput.addEventListener('blur', () => {
			passwordMessage.style.display = "none";
		});

		myInput.addEventListener('input', () => {
			const password = myInput.value;

			// Validate lowercase letters
			const hasLowerCase = /[a-z]/.test(password);
			letterIcon.classList.toggle('valid', hasLowerCase);
			letterIcon.classList.toggle('invalid', !hasLowerCase);

			// Validate capital letters
			const hasUpperCase = /[A-Z]/.test(password);
			capital.classList.toggle('valid', hasUpperCase);
			capital.classList.toggle('invalid', !hasUpperCase);

			// Validate numbers
			const hasNumber = /\d/.test(password);
			number.classList.toggle('valid', hasNumber);
			number.classList.toggle('invalid', !hasNumber);

			// Validate length
			const hasMinLength = password.length >= 8;
			length.classList.toggle('valid', hasMinLength);
			length.classList.toggle('invalid', !hasMinLength);
		});
	}

	// Email validation
	if (emailInput) {
		emailInput.addEventListener('input', () => {
			emailInput.classList.toggle('invalid', !isValidEmail(emailInput.value));
		});
	}

	// Phone number validation
	if (phoneNumberInput) {
		phoneNumberInput.addEventListener('input', () => {
			let inputValue = phoneNumberInput.value.replace(/\D/g, ''); // Remove non-numeric characters
			inputValue = inputValue.slice(0, 10); // Keep only the first 10 digits
			phoneNumberInput.value = inputValue; // Update the input value
			phoneNumberInput.classList.toggle('invalid', !isValidPhoneNumber(inputValue));
		});
	}	
	

});

/*User Dashboard Sidebae*/
const toggleSidebar =()=>{
    const sidebar = document.querySelector('.sidebar');
    const content = document.querySelector('.content');
    if (sidebar.style.display === "none") {
        sidebar.style.display = "block";
        content.style.marginLeft = "20%";
    } else {
        sidebar.style.display = "none";
        content.style.marginLeft = "0%";
    }
};

/*User Dashboard FileUploard*/

