package org.example.data.demo;


import org.example.data.Movie;
import org.junit.jupiter.api.Test;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieDemo {

    @Test
    void demoGetterSetterTitle(){
        System.out.println("*** demoGetterSetterTitle ***");
        Movie movie = new Movie();
        System.out.println("default title: " + movie.getTitle());
        movie.setTitle("Oppenheimer");
        System.out.println("title modified: " +movie.getTitle());
        System.out.println("movie object: " + movie); // Oppenheimer (0)
        System.out.println();
    }

    @Test
    void demoConstructorsAndArray() {
        System.out.println("*** demoConstructors ***");
        // default constructor
        Movie movie1 = new Movie();
        // constructor with title, year
        Movie movie2 = new Movie("Barbie", 2023);
        // constructor with all fields
        Movie movie3 = new Movie("Oppenheimer", 2023, 180);
        // build Movie
        Movie movie4 = Movie.builder()
                .title("The Batman")
                .year(2022)
                .build();

        // with a loop display these 3 movies
        // - null (0)
        // - Barbie (2023)
        // - Oppenheimer (2023)

        Movie[] tabMovies = { movie1, movie2, movie3, movie4 };
        // oldschool loop
        for (int i = 0; i < 3; i++){
            // NB: following line can be simplified by omitting .toString()
            // System.out.println(" - " + tabMovies[i].toString());
            System.out.println(" - "
                    + tabMovies[i]
                    + " ; duration = "
                    + tabMovies[i].getDuration()
                    + " mn"
            );
        }
        System.out.println();
        // "foreach" loop (Java 5)
        for (Movie movie: tabMovies){
            System.out.println(" * " + movie
                    + " ; duration = "
                    + movie.getDuration()
            );
        }

        System.out.println();
    }

    @Test
    void demoArrayListMovies() {
        System.out.println("*** demoArrayListMovies ***");
        // create list with movies
        // - Barbie (2023)
        // - Oppenheimer (2023)
        // - The Batman (2022)
        // - Star Wars: Episode IV - A New Hope (1977)
        List<Movie> listMovies = new ArrayList<>(); // or new LinkedList<>()
        listMovies.add(new Movie("Barbie", 2023));
        listMovies.add(new Movie("Oppenheimer", 2023));
        Collections.addAll(
                listMovies,
                new Movie("The Batman", 2022),
                new Movie("Star Wars: Episode IV - A New Hope", 1977),
                new Movie("Indiana Jones and the Last Crusade", 1989)
        );
        for (Movie movie: listMovies) {
            System.out.println(movie);
        }
        System.out.println();
    }

    @Test
    void demoListOfMovies() {
        System.out.println("*** demoListOfMovies ***");
        // Java 11 (introduced in Java 9): List.of
        List<Movie> movieList = List.of(
                new Movie("Barbie", 2023),
                new Movie("Oppenheimer", 2023),
                new Movie("The Batman", 2022),
                new Movie("Star Wars: Episode IV - A New Hope", 1977),
                new Movie("Indiana Jones and the Last Crusade", 1989)
        );
        // NB: this list is unmodifiable !
        // throws Exception: java.lang.UnsupportedOperationException
        // movieList.add(new Movie("Indiana Jones and the Dial of Destiny", 2023));
        System.out.println(movieList);
        System.out.println("Real type of this list: " + movieList.getClass());
        for (Movie movie: movieList) {
            System.out.println(" ~ " + movie);
        }
        System.out.println();
    }

}