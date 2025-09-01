const cities = ["Mumbai", "Chennai", "Delhi", "Hyderabad", "Bangalore", "Kolkata", "Pune"];

function populateDropdown() {
  const dropdown = document.getElementById("cityDropdown");

  
  dropdown.length = 1;

  
  const sortedCities = cities.slice().sort();

  
  sortedCities.forEach(city => {
    const option = document.createElement("option");
    option.value = city;
    option.textContent = city;
    dropdown.appendChild(option);
  });
}