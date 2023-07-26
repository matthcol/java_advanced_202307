package org.example.data.demo;

import org.example.data.Movie;
import org.example.data.provider.MovieProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

class MovieStreamDemo {

    static List<Movie> movieList;

    @BeforeAll
    static void initData() {
        movieList = List.of(
                new Movie("Barbie", 2023),
                new Movie("Oppenheimer", 2023, 180),
                new Movie("The Batman", 2022, 176),
                new Movie("Star Wars: Episode IV - A New Hope", 1977, 121),
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

    @Test
    void demoJoining() {
        String titles = movieList.stream()
                .map(Movie::getTitle)
                .collect(Collectors.joining(", "));
        System.out.println(titles);
        System.out.println();
    }

    @Test
    void demoStatistics(){
        long nbMovie2023 = movieList.stream()
                .filter(movie -> movie.getYear() == 2023)
                .count();
        System.out.println("Movies of year 2023: " + nbMovie2023);

        OptionalInt minYear = movieList.stream()
                .mapToInt(Movie::getYear)
                .min();
        System.out.println("Minimum year: " + minYear);

        OptionalInt maxYear = movieList.stream()
                .mapToInt(Movie::getYear)
                .filter(year -> year < 1970)
                .max();
        System.out.println("Maximum year: " + maxYear);

        var totalDuration = movieList.stream()
                .filter(movie -> Objects.nonNull(movie.getDuration()))
                .mapToInt(Movie::getDuration)
                .sum();
        System.out.println("Total duration: " + totalDuration + " mn");

        IntSummaryStatistics stats = movieList.stream()
                .filter(movie -> Objects.nonNull(movie.getDuration()))
                .mapToInt(Movie::getDuration)
                .summaryStatistics();
        System.out.println(stats);

        IntSummaryStatistics stats2 = movieList.stream()
                .filter(movie -> movie.getYear() == 3035)
                .mapToInt(Movie::getDuration)
                .summaryStatistics();
        System.out.println(stats2); // min = MAX_INT, max = MIN_INT, avg = 0.0 !
    }

    @Test
    void extractDataFromEmptyOptional() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            Integer duration = null;
            int durationPrimitive = duration;
        });
    }

}
