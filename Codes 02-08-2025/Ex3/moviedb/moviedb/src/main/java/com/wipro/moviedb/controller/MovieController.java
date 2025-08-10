package com.wipro.moviedb.controller;

import com.wipro.moviedb.model.Movie;
import com.wipro.moviedb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return service.saveMovie(movie);
    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return service.getAllMovies();
    }
}