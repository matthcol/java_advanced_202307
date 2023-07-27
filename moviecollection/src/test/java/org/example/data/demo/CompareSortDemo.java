package org.example.data.demo;

import org.example.data.Movie;
import org.example.data.provider.MovieProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Stream;

class CompareSortDemo {

    @ParameterizedTest
    @CsvSource({
            "3,5",
            "5,3",
            "3,3"
    })
    void comparePrimitives(int x, int y) {
        System.out.println(MessageFormat.format(
                "Compare x={0} and y={1}", x, y));
        System.out.println("x == y: " + (x == y));
        System.out.println("x != y: " + (x != y));
        System.out.println("x < y: " + (x < y));
        System.out.println("x <= y: " + (x <= y));
        System.out.println("x > y: " + (x > y));
        System.out.println("x >= y: " + (x >= y));
        System.out.println();
    }

    @ParameterizedTest
    @CsvSource({
          "PAU,PARIS",
          "PAU,PAU",
          "PARIS,PAU"
    })
    void compareStrings(String text1, String text2){
        System.out.println(MessageFormat.format(
                "Compare {0} and {1}", text1, text2));
        // same objects in memory: ==, !=
        boolean areSame = text1 == text2;
        // equality relation
        boolean areEquals = text1.equals(text2);
        // order relation: String implements Comparable<String>
        // "Natural Order" on type String
        int compareResult = text1.compareTo(text2);

        // display results:
        System.out.println(MessageFormat.format(
                "{0} == {1}: {2}",  text1, text2, areSame));
        System.out.println(MessageFormat.format(
                "{0} equals {1}: {2}",  text1, text2, areEquals));
        System.out.println(MessageFormat.format(
                "{0} compareTo {1}: {2}",  text1, text2, compareResult));
        System.out.println();
    }

    @ParameterizedTest
    @ValueSource(strings = {"PAU"})
    @NullSource
    void demoEqualsStringWithNull(String text){
        boolean areEquals1 = Objects.equals(text, null);
        boolean areEquals2 = Objects.equals(null, text);
        System.out.println(MessageFormat.format(
                "{0} equals {1}: {2}", text, null, areEquals1));
        System.out.println(MessageFormat.format(
                "{0} equals {1}: {2}", null, text, areEquals2));
    }

    @ParameterizedTest
    @ValueSource(strings = {"PAU"})
    @NullSource
    void demoCompareStringWithNull(String text){
        // dynamic error: null is not comparable with anything
        // except if you define an specific
        int compare1 = text.compareTo(null);
        System.out.println(MessageFormat.format(
                "{0} compareTo {1}: {2}", text, null, compare1));
    }


    @Test
    void demoSortPrimitives(){
        int[] temperatures = {12, 23, 40, -10, 7, 13, 23};
        System.out.println(Arrays.toString(temperatures));
        Arrays.sort(temperatures);
        System.out.println(Arrays.toString(temperatures));
        System.out.println();
    }

    @Test
    void demoSortStrings(){
        List<String> cities = new ArrayList<>();
        Collections.addAll(cities, "PAU", "PARIS", "TOULOUSE", "MARSEILLE",
                "LYON", "BORDEAUX", "BAYONNE");
        System.out.println(cities);
        Collections.sort(cities); // use Natural Order
        System.out.println(cities);
        System.out.println();
    }

    @Test
    void demoSortStringsIgnoringCase(){
        List<String> cities = new ArrayList<>();
        Collections.addAll(cities, "PAU", "paris", "Toulouse",
                "touLON",
                "MaRsEiLlE",
                "LYON", "bORDEAUx", "BaYONNe");
        System.out.println(cities);
        // use Natural Order
        Collections.sort(cities);
        System.out.println(cities);
        // use Comparator as a reference function
        Collections.sort(cities, String::compareToIgnoreCase);
        System.out.println(cities);
        // use Comparator as a lambda function
        Collections.sort(cities, (city1, city2) -> city1.compareToIgnoreCase(city2)); // use Comparator
        System.out.println(cities);
        System.out.println();
    }

    @Test
    void demoSortMovies(){
        List<Movie> movies = new ArrayList<>();
        Collections.addAll(movies,
                new Movie("Barbie", 2023),
                new Movie("Oppenheimer", 2023),
                new Movie("The Batman", 2022),
                new Movie("Star Wars: Episode IV - A New Hope", 1977),
                new Movie("Indiana Jones and the Last Crusade", 1989)
        );
        // By default: Error execution: type Movie is not Comparable
        // OK if Movie implements Comparable<Movie>
        NavigableSet<Movie> movieSorted = new TreeSet<>(movies);
        System.out.println(movieSorted);
        // By default: Error compilation: type Movie is not Comparable
        // OK if Movie implements Comparable<Movie>
        Collections.sort(movies);
        System.out.println(movies);
    }

    static Stream<Arguments> movieComparators() {
        // 1 - dummy comparator
        Comparator<Movie> comparatorDummy = (movie1, movie2)  -> -1;
        // 2 - natural order
        Comparator<Movie> comparatorNaturalOrder = Comparator.naturalOrder();
        // 2bis - natural order
        Comparator<Movie> comparatorNaturalOrder2 = Movie::compareTo;
        // 3 - year asc, title asc
        Comparator<Movie> comparatorYearTitle = Comparator.comparingInt(Movie::getYear)
                .thenComparing(Movie::getTitle);
        // 4 - year desc, title asc ignoring case
        Comparator<Movie> comparatorYearDescTitleI =
                Comparator.comparing(Movie::getYear, Comparator.reverseOrder())
                        .thenComparing(Movie::getTitle, String::compareToIgnoreCase);
        // 5 - duration asc null after, title asc
        Comparator<Movie> comparatorDurationTitle =
                Comparator.comparing(
                            Movie::getDuration,
                            Comparator.nullsLast(Comparator.naturalOrder())
                        )
                        .thenComparing(Movie::getTitle);
        // 6 - duration desc null after, title asc
        Comparator<Movie> comparatorDurationDescTitle =
                Comparator.comparing(Movie::getDuration,
                                Comparator.nullsLast(Comparator.reverseOrder()))
                        .thenComparing(Movie::getTitle);
        return Stream.of(
                Arguments.of(
                        comparatorDummy,
                        "Dummy comparator"
                ),
                Arguments.of(
                      comparatorNaturalOrder,
                      "comparatorNaturalOrder"
                ),
                Arguments.of(
                        comparatorNaturalOrder2,
                        "comparatorNaturalOrder (method reference)"
                ),
                Arguments.of(
                        comparatorYearTitle,
                        "comparatorYearTitle"
                ),
                Arguments.of(
                        comparatorYearDescTitleI,
                        "comparatorYearDescTitleI"
                ),
                Arguments.of(
                        comparatorDurationTitle,
                        "comparatorDurationTitle"
                ),
                Arguments.of(
                        comparatorDurationDescTitle,
                        "comparatorDurationDescTitle"
                )
        );
    }
    @ParameterizedTest
    @MethodSource("movieComparators")
    void demoSortWithComparator(Comparator<Movie> cmp, String message){
        long countMovieToObserve = 20L;
        System.out.println("Sort movies by: " + message);
        List<Movie> movies = new ArrayList<>(MovieProvider.movieBigList());
        Collections.sort(movies, cmp);
        System.out.println("Collections.sort(list, cmp): " + movies.stream().limit(countMovieToObserve).toList());
        movies.sort(cmp);
        System.out.println("List.sort(cmp): " + movies.stream().limit(countMovieToObserve).toList());
        NavigableSet<Movie> movieSorted = new TreeSet<>(cmp);
        movieSorted.addAll(movies);
        System.out.println("TreeSet: " + movieSorted.stream().limit(countMovieToObserve).toList());
        System.out.println();
    }

}
