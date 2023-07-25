package org.example;

import org.example.data.Movie;

public class Main {
    public static void main(String[] args) {
        Movie movie = new Movie();
        System.out.println("Hello world!");
        System.out.println("Movie: " + movie);
        System.out.println("Movie title: " + movie.getTitle());
        movie.setTitle("Barbie");
        System.out.println("Movie title: " + movie.getTitle());
    }
}