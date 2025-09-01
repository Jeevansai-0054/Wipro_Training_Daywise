package com.wipro.moviedb.repository;

import com.wipro.moviedb.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
}