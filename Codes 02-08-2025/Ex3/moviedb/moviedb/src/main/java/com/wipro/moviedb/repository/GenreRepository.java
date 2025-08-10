package com.wipro.moviedb.repository;

import com.wipro.moviedb.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}