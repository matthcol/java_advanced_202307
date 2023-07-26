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
        var moviesFrom1984 = movieList.stream()
                .filter(movie -> movie.getYear() == 1984)
                .toList();

        System.out.println(moviesFrom1984);
    }

    @Test
    void exerciseListTitlesOfMovieFromYear1984(){
        var titleMoviesFrom1984 = movieList.stream()
                .filter(movie -> movie.getYear() == 1984)
                .map(Movie::getTitle)
                .toList();
        System.out.println(titleMoviesFrom1984);
    }

    @Test
    void exerciceListTitlesOfMovieBefore1950BeginningByR(){
        System.out.println("Liste des films avant 1950 :");

        var listOldMovieStartingWithR = movieList.stream()
                .filter(movie -> (movie.getYear() < 1950)
                            && movie.getTitle().startsWith("R"))
                .map(Movie::getTitle)
                .peek(System.out::println)
                .toList();
        System.out.println(listOldMovieStartingWithR);
    }

    @Test
    void exerciseTotalDurationMoviesFromYears2000s() {
        int totalDuration = movieList.stream()
                .filter(movie -> (movie.getYear() >= 2000)
                        && (movie.getYear() <= 2009))
                // .mapToInt(movie -> movie.getDuration() != null ? movie.getDuration() : 0)
                .filter(movie -> Objects.nonNull(movie.getDuration()))
                .mapToInt(Movie::getDuration)
                .sum();

        System.out.println("Durée total des films des années 2000s: " + totalDuration + " minutes");
    }

}
