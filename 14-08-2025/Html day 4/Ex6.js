// ✅ Array of movie objects with releaseDate
const movies = [
  {
    movieName: "Inception",
    movieLanguage: "English",
    imdbRating: 8.8,
    releaseDate: "2010-07-16"
  },
  {
    movieName: "Parasite",
    movieLanguage: "Korean",
    imdbRating: 8.6,
    releaseDate: "2019-05-30"
  },
  {
    movieName: "Interstellar",
    movieLanguage: "English",
    imdbRating: 8.7,
    releaseDate: "2014-11-07"
  },
  {
    movieName: "Spirited Away",
    movieLanguage: "Japanese",
    imdbRating: 8.6,
    releaseDate: "2001-07-20"
  },
  {
    movieName: "3 Idiots",
    movieLanguage: "Hindi",
    imdbRating: 8.4,
    releaseDate: "2009-12-25"
  }
];


movies.forEach(({ movieName, movieLanguage, imdbRating, releaseDate }, index) => {
  console.log(`🎬 Movie ${index + 1}`);
  console.log(`Name        : ${movieName}`);
  console.log(`Language    : ${movieLanguage}`);
  console.log(`IMDB Rating : ${imdbRating}`);
  console.log(`Release Date: ${releaseDate}`);
 
});