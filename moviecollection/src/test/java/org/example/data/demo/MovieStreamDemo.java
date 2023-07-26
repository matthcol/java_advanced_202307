package org.example.data.demo;

import org.example.data.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

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

    @Test
    void demoMapPrintTitles(){
        movieList.stream()
                .map(movie -> movie.getTitle())
                .forEach(title -> System.out.println(title));
        System.out.println();
        movieList.stream()
                .map(Movie::getTitle)
                .forEach(System.out::println);
        System.out.println();
    }

    @Test
    void demoMapFilterTitlesOfYear2023(){
        movieList.stream()
                .peek(movie -> System.out.println("Peek movie before filter: " + movie))
                .filter(movie -> movie.getYear() == 2023)
                .peek(movie -> System.out.println("Peek movie after filter: " + movie))
                .map(Movie::getTitle)
                .peek(title -> System.out.println("Peek title: " + title))
                .forEach(System.out::println);
        System.out.println();
    }

    @Test
    void demoSkipLimit() {
        movieList.stream()
                .limit(2)
                .forEach(System.out::println);
        System.out.println();
        movieList.stream()
                .skip(2)
                .limit(2)
                .forEach(System.out::println);
        System.out.println();
    }

    @Test
    void demoToCollection(){
        List<Movie> movieList2023 = movieList.stream()
                .filter(movie -> movie.getYear() == 2023)
                .toList(); // Java 17
        System.out.println(movieList2023);
        System.out.println(movieList2023.getClass());
        System.out.println();

        List<Movie> movieList2023bis = movieList.stream()
                .filter(movie -> movie.getYear() == 2023)
//                .collect(Collectors.toCollection(() -> new ArrayList<>()));
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(movieList2023bis);
        System.out.println(movieList2023bis.getClass());
        System.out.println();

        // movieSet2023 is seen as HashSet<Movie>, can be declared Set<Movie>
        var movieSet2023 = movieList.stream()
                .filter(movie -> movie.getYear() == 2023)
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println(movieSet2023);
        System.out.println(movieSet2023.getClass());
        System.out.println();

        // Error: Movie objects are not Comparable
        //        var movieSet2023bis = movieList.stream()
        //                .filter(movie -> movie.getYear() == 2023)
        //                .collect(Collectors.toCollection(() -> new TreeSet<>()));
        var titleSet = movieList.stream()
                .filter(movie -> movie.getYear() != 2023)
                .map(Movie::getTitle)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(titleSet);
        System.out.println();
    }

}
