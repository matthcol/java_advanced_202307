package org.example.data.demo;

import org.example.data.Movie;
import org.example.data.Person;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MovieDemoGenresDirectorActors {

    @Test
    void demoGenres(){
        var movie = Movie.builder()
                .title("The Batman")
                .year(2022)
                .genres(Set.of("Action", "Crime", "Drama"))
                .build();
        System.out.println(movie
            + " ; genres="
            + movie.getGenres()
        );
        movie.getGenres().add("Dummy"); // Unsupported Operation Exception
    }

    @Test
    void demoGenres2(){
        var movie = Movie.builder()
                .title("The Batman")
                .year(2022)
                .build();
        System.out.println(movie
                + " ; genres="
                + movie.getGenres()
        );
        Collections.addAll(movie.getGenres(), "Action", "Crime", "Drama");
        System.out.println(movie
                + " ; genres="
                + movie.getGenres()
        );
        movie.getGenres().add("Action");
        System.out.println(movie
                + " ; genres="
                + movie.getGenres()
        );
    }

    @Test
    void demoMovieDirector(){
        var person = new Person("Matt Reeves");
        var movie = Movie.builder()
                .title("The Batman")
                .year(2022)
                .director(person)
                .build();
        System.out.println(movie
                + " ; director="
                + movie.getDirector()
        );

    }

    @Test
    void demoMovieActors(){
        var person1 = new Person("Robert Pattinson");
        var person2 = new Person("Zoë Kravitz");
        var movie = Movie.builder()
                .title("The Batman")
                .year(2022)
                .build();
        Collections.addAll(movie.getActors(), person1, person2);
        System.out.println(movie
                + " ; actors="
                + movie.getActors()
        );

    }



}