package org.example.data.exercise;

import org.example.data.Movie;
import org.example.data.provider.MovieProvider;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

public class MovieStreamExercise {

    static List<Movie> movieList = MovieProvider.movieBigList();

    @Test
    void exerciseCountMoviesWithoutDuration(){
        long count = movieList.stream()
                .filter(movie -> Objects.isNull(movie.getDuration()))
                .count();

        System.out.println("Nombre des films sans durée : " + count);
    }

    @Test
    void exerciseListMoviesFromYear1984(){
        // TODO
    }

    @Test
    void exerciseListTitlesOfMovieFromYear1984(){
        // TODO
    }

    @Test
    void exerciceListTitlesOfMovieBefore1950BeginningByR(){
        // TODO
    }

    @Test
    void exerciseTotalDurationMoviesFromYears2000s() {
        // TODO
    }

}
