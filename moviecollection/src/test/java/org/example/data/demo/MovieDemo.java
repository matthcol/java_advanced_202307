package org.example.data.demo;


import org.example.data.Movie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovieDemo {

    @Test
    void demoGetterSetterTitle(){
        Movie movie = new Movie();
        System.out.println(movie.getTitle());
        movie.setTitle("Oppenheimer");
        System.out.println(movie.getTitle());
    }

    @Test
    void demoConstructors() {
        // default constructor
        Movie movie1 = new Movie();

        // constructor with title, year
        Movie movie2 = new Movie("Barbie", 2023);

        // constructor with all fields
        Movie movie3 = new Movie("Oppenheimer", 2023, 180);

    }

}