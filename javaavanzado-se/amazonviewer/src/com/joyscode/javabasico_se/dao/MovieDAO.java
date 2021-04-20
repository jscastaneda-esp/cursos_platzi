package com.joyscode.javabasico_se.dao;

import java.util.ArrayList;
import java.util.List;

import com.joyscode.javabasico_se.model.Movie;

public interface MovieDAO {

    default Movie setMovieViewed(Movie movie) {
        return movie;
    }
    
    default List<Movie> read() {
        List<Movie> movies = new ArrayList<>();
        return movies;
    }
}
