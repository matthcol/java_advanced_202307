package org.example.data.demo;


import org.example.data.Movie;
import org.junit.jupiter.api.Test;

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
    void demoConstructors() {
        System.out.println("*** demoConstructors ***");
        // default constructor
        Movie movie1 = new Movie();

        // constructor with title, year
        Movie movie2 = new Movie("Barbie", 2023);

        // constructor with all fields
        Movie movie3 = new Movie("Oppenheimer", 2023, 180);

        // with a loop display these 3 movies
        // - null (0)
        // - Barbie (2023)
        // - Oppenheimer (2023)

        Movie tab[] ={ movie1,movie2,movie3};
        for(int i= 0;i<3; i++){
            System.out.println(tab[i].toString());
        }
        System.out.println();
    }

}