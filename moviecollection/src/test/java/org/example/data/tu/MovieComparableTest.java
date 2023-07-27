package org.example.data.tu;

import org.example.data.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MovieComparableTest {

    static Stream<Arguments> moviesLessThan(){
        return Stream.of(
                Arguments.of(
                        new Movie("Alien", 1979),
                        new Movie("Batman begins", 2005)
                ),
                Arguments.of(
                        new Movie("The Lion King", 1994),
                        new Movie("The Lion King", 2019)
                )
        );
    }

    static Stream<Arguments> moviesGreaterThan(){
        return Stream.of(
                Arguments.of(
                        new Movie("Batman begins", 2005),
                        new Movie("Alien", 1979)
                ),
                Arguments.of(
                        new Movie("The Lion King", 2019),
                        new Movie("The Lion King", 1994)
                )
        );
    }

    static Stream<Arguments> moviesEquals(){
        return Stream.of(
                Arguments.of(
                        new Movie("Alien", 1979),
                        new Movie("Alien", 1979)
                ),
                Arguments.of(
                        new Movie("Alien", 1979, 117),
                        new Movie("Alien", 1979, 117)
                ),
                Arguments.of(
                        new Movie("Alien", 1979, 117),
                        new Movie("Alien", 1979, 118)  // duration does not count
                ),
                Arguments.of(
                        new Movie("Alien", 1979, 117),
                        new Movie("Alien", 1979)
                )
        );
    }
    @ParameterizedTest
    @MethodSource("moviesLessThan")
    void testMovieLessThan(Movie movie1, Movie movie2){
        assertTrue(movie1.compareTo(movie2) < 0, "movie1 < movie2");
    }

    @ParameterizedTest
    @MethodSource("moviesGreaterThan")
    void testMovieGreaterThan(Movie movie1, Movie movie2){
        assertTrue(movie1.compareTo(movie2) > 0, "movie1 > movie2");
    }

    @ParameterizedTest
    @MethodSource("moviesEquals")
    void testMovieEquals(Movie movie1, Movie movie2){
        assertTrue(movie1.compareTo(movie2) == 0, "movie1 == movie2");
    }

}