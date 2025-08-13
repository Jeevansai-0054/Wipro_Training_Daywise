const emailInput = document.getElementById("email");
const passwordInput = document.getElementById("password");
const emailError = document.getElementById("emailError");
const passwordError = document.getElementById("passwordError");

const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;


emailInput.addEventListener("blur", () => {
  validateEmail();
});


passwordInput.addEventListener("blur", () => {
  validatePassword();
});


function validateForm() {
  const isEmailValid = validateEmail();
  const isPasswordValid = validatePassword();

  if (isEmailValid && isPasswordValid) {
    alert("Login successful!");
  }
}


function validateEmail() {
  const email = emailInput.value.trim();
  if (email === "") {
    emailError.textContent = "Email is required.";
    return false;
  } else if (!emailRegex.test(email)) {
    emailError.textContent = "Please enter a valid email.";
    return false;
  } else {
    emailError.textContent = "";
    return true;
  }
}


function validatePassword() {
  const password = passwordInput.value.trim();
  if (password === "") {
    passwordError.textContent = "Password is required.";
    return false;
  } else {
    passwordError.textContent = "";
    return true;
  }
}