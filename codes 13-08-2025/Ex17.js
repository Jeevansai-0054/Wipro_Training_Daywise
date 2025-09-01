const questions = [
  {
    question: "What is the capital of India?",
    options: ["Mumbai", "Delhi", "Kolkata", "Chennai"],
    answer: "Delhi"
  },
  {
    question: "Which language runs in a web browser?",
    options: ["Java", "C", "Python", "JavaScript"],
    answer: "JavaScript"
  },
  {
    question: "What does CSS stand for?",
    options: ["Cascading Style Sheets", "Color Style Sheets", "Computer Style Sheets", "Creative Style Syntax"],
    answer: "Cascading Style Sheets"
  }
];

let currentQuestion = 0;
let score = 0;

function loadQuestion() {
  const q = questions[currentQuestion];
  document.getElementById("question").textContent = q.question;

  const optionsList = document.getElementById("options");
  optionsList.innerHTML = "";

  q.options.forEach(option => {
    const li = document.createElement("li");
    li.textContent = option;
    li.onclick = () => checkAnswer(option);
    optionsList.appendChild(li);
  });

  document.getElementById("feedback").textContent = "";
}

function checkAnswer(selected) {
  const correct = questions[currentQuestion].answer;
  const feedback = document.getElementById("feedback");

  if (selected === correct) {
    feedback.textContent = "✅ Correct!";
    score++;
  } else {
    feedback.textContent = "❌ Wrong!";
  }

  document.getElementById("scoreDisplay").textContent = `Score: ${score}`;

  // Disable further clicks
  const options = document.querySelectorAll("#options li");
  options.forEach(li => li.onclick = null);
}

function nextQuestion() {
  currentQuestion++;
  if (currentQuestion < questions.length) {
    loadQuestion();
  } else {
    document.getElementById("question").textContent = "🎉 Quiz Completed!";
    document.getElementById("options").innerHTML = "";
    document.getElementById("feedback").textContent = `Final Score: ${score}/${questions.length}`;
    document.querySelector("button").style.display = "none";
  }
}

// Load first question
loadQuestion();