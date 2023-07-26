package org.example.data.exercise;

import org.example.data.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MovieCollectionExercise {

    static List<Movie> movieList;

    @BeforeAll
    static void initData() {
        movieList = List.of(
                new Movie("Barbie", 2023),
                new Movie("Oppenheimer", 2023),
                new Movie("The Batman", 2022),
                new Movie("Star Wars: Episode IV - A New Hope", 1977),
                new Movie("Indiana Jones and the Last Crusade", 1989)
        );
    }

    @Test
    void exerciseSet() {
        System.out.println("*** Exercise Set ***");
        System.out.println("Movie list (init): "  + movieList);
        // TODO: copy all movies in a Set and print it
        // Method 1: add movies from another collection with a loop
        Set<Movie> movieSet = new HashSet<>();
        for (Movie movie : movieList){
            movieSet.add(movie);
        }
        System.out.println("Movie set (foreach): "  +movieSet);

        // Method 2: with constructor
        Set<Movie> movieSet2 = new HashSet<>(movieList);
        System.out.println("Movie set (constructor): "  +movieSet2);

        // Method 3: ??
        Set<Movie> movieSet3 = new HashSet<>();
        movieSet3.addAll(movieList);
        System.out.println("Movie set (addAll): "  +movieSet3);
    }

    @Test
    void exerciseMap() {
        System.out.println("*** Exercise Map ***");
        System.out.println("Movie list (init): " + movieList);
        // TODO:
        //  1 - index movie list in a Map<String, Movie>
        //  2 - print map
        //  3 - search 'The Batman' and print it if exists
        //  3 - search 'Batman Begins' and print if if exists
    }
}
