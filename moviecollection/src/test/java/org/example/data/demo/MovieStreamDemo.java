package org.example.data.demo;

import org.example.data.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class MovieStreamDemo {

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
    void demoStreamForEach() {
        movieList.stream()
                .forEach(System.out::println);
        System.out.println();
        // NB: .forEach can be called directly on collections
        movieList.forEach(System.out::println);
        System.out.println();
        // with a lambda/anonymous function
        movieList.forEach(movie -> System.out.print(movie + " "));
        System.out.println();
        System.out.println();
    }
}
