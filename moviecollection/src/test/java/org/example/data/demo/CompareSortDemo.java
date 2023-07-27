package org.example.data.demo;

import org.example.data.Movie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.MessageFormat;
import java.util.*;

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
        // Error compilation: type Movie is not Comparable
        // Collections.sort(movies);
        // Error execution: type Movie is not Comparable
        // NavigableSet<Movie> movieSorted = new TreeSet<>(movies);
    }
}
