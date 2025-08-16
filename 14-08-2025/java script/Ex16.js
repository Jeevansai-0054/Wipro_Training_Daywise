const images = [
  "images/p9.jpg",
  "images/p10.jpg",
  "images/p7.jpg",
  "images/p8.jpg"
];

let currentIndex = 0;
const galleryImage = document.getElementById("galleryImage");


galleryImage.src = images[currentIndex];

function showNext() {
  currentIndex = (currentIndex + 1) % images.length;
  fadeImage(images[currentIndex]);
}

function showPrev() {
  currentIndex = (currentIndex - 1 + images.length) % images.length;
  fadeImage(images[currentIndex]);
}

function fadeImage(src) {
  galleryImage.style.opacity = 0;
  setTimeout(() => {
    galleryImage.src = src;
    galleryImage.style.opacity = 1;
  }, 300);
}