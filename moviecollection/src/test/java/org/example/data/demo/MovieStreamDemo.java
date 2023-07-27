package org.example.data.demo;

import org.example.data.Movie;
import org.example.data.provider.MovieProvider;
import org.example.function.IntBiPredicate;
import org.example.function.IntTernaryOperator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.*;
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    @Test
    void demoLambdaFunctions(){
        String[] texts = { "Paris,Toulouse,Pau", "pêche,fraise,banane"};
        Arrays.stream(texts)
                .map((String text) -> text.split(","))
                .forEach(splitText -> System.out.println(Arrays.toString(splitText)));

        Function<String, String[]> f = (String text) -> text.split(",");
        var res = f.apply("Vignemale,Néouvielle,Pic du Midi d'Ossau");
        System.out.println(Arrays.toString(res));

        Predicate<String> p = String::isBlank;  // p -> p.isBlank()
        var res2 = p.test("  \t  \n\r ");
        System.out.println(res2);

//        Function<Integer, Set<String>> constructor = HashSet::new;
        IntFunction<Set<String>> constructor = HashSet::new;
        Set<String> set1 = constructor.apply(1000);
        System.out.println(set1);

        Supplier<Set<String>> constructor2 = HashSet::new;
        Set<String> set2 = constructor2.get();
        System.out.println(set2);

        Function<String, String> funString = String::toLowerCase;
        String newString = funString.apply("AzErTy");
        System.out.println(newString);

        UnaryOperator<String> funString2 = String::toLowerCase;
        String newString2 = funString2.apply("aZeRtY");
        System.out.println(newString2);

        // Exercise: choose an appropriate type for these lambdas
        // in java.util.function

        // NB: use autoboxing:
        // Function<Integer, Integer>
        // UnaryOperator<Integer>, IntFunction<Integer>,
        // ToIntFunction<Integer>
        Function<Integer,Integer> fun1 = x -> x * x + 1;
        // NB: without autoboxing
        IntUnaryOperator fun1bis = x -> x * x + 1;

        // DoubleBinaryOperator or IntBinaryOperator
        DoubleBinaryOperator fun2 = (x,y) -> (x * y) + 2 * (x + y); // int or double

        LongPredicate fun3 = x -> x >= 1_000_000_000_000_000_000L; // long -> boolean

        // IntBiPredicate does not exist !
        // => can use autoboxing with BiPredicate<Integer, Integer> :(
        IntBiPredicate fun4 = (x, y) -> x < 10 * y;  // 2 x int or double -> boolean
        IntTernaryOperator fun5 = (x, y, z) -> x + 2 * y + 3 * z;

        System.out.println(fun1.apply(3));
        System.out.println(fun3.test(Long.MAX_VALUE));
        System.out.println(fun4.test(45, 5));
        System.out.println(fun5.apply(1,2,3));
    }

    @Test
    void demoStreamOf(){
        var movie1 = new Movie("Barbie", 2023);
        var movie2 = new Movie("Oppenheimer", 2023);
        var movie3 = new Movie("The Batman", 2022);
        Stream.of(movie1, movie2, movie3)
                .forEach(System.out::println);
    }

    @Test
    void demoGenerator(){
        IntStream.range(0, 10)
                .forEach(i -> System.out.print(i + " "));
        System.out.println();
        IntStream.rangeClosed(1, 10)
                .forEach(i -> System.out.print(i + " "));
        System.out.println();
        IntStream.generate(() -> 3)  // () -> int
                .limit(10)
                .forEach(i -> System.out.print(i + " "));
        System.out.println();
        DoubleStream.generate(() ->
                    RandomGenerator.getDefault().nextGaussian(10.0, 3.5)
                )
                .limit(30)
                .forEach(i -> System.out.print(i + " "));
        System.out.println();
        IntStream.iterate(0, x -> x + 3)
                .limit(20)
                .forEach(i -> System.out.print(i + " "));
        System.out.println();
        IntStream.iterate(2, x -> x < 50_000, x -> x * x)
                .forEach(i -> System.out.print(i + " "));
        System.out.println();
    }
}
