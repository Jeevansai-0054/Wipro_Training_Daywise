const inputBox = document.getElementById("textInput");
const label = document.getElementById("charCountLabel");

inputBox.addEventListener("input", () => {
  const remaining = 50 - inputBox.value.length;
  label.textContent = `${remaining} characters remaining`;
});