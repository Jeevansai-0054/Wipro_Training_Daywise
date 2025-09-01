package com.wipro.moviedb.service;

import com.wipro.moviedb.model.Movie;
import com.wipro.moviedb.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repo;

    public Movie saveMovie(Movie movie) {
        return repo.save(movie);
    }

    public List<Movie> getAllMovies() {
        return repo.findAll();
    }
}