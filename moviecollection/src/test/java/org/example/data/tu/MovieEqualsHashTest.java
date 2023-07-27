package org.example.data.tu;

import org.example.data.Movie;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MovieEqualsHashTest {
    static Stream<Arguments> moviesNotEquals(){
        return Stream.of(
                Arguments.of(
                        new Movie("Alien", 1979),
                        new Movie("Apocalypse Now", 1979) // different title
                ),
                Arguments.of(
                        new Movie("The Lion King", 1994),
                        new Movie("The Lion King", 2019) // different year
                ),
                Arguments.of(
                        new Movie("The Batman", 2022),
                        null
                ),
                Arguments.of(
                        new Movie("The Batman", 2022),
                        "The Batman"  // different than a String
                )
        );
    }

    static Stream<Arguments> moviesEquals(){
        var movie = new Movie("The Batman", 2022);
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
                ),
                Arguments.of(movie, movie) // same object in memory
        );
    }

    @ParameterizedTest
    @MethodSource("moviesEquals")
    void testMovieEquals(Movie movie1, Movie movie2){
        assertAll(
                () -> assertTrue(Objects.equals(movie1, movie2), "movie1 == movie2"),
                () -> assertTrue(Objects.equals(movie2, movie1), "movie2 == movie1")
        );
    }

    @ParameterizedTest
    @MethodSource("moviesNotEquals")
    void testMovieGreaterThan(Movie movie, Object other){
        assertAll(
                () -> assertFalse(Objects.equals(movie, other), "movie != other"),
                () -> assertFalse(Objects.equals(movie, other), "other != movie")
        );
    }

    // TODO: check consistency between equals and hash code


}