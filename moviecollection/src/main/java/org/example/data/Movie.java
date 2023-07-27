package org.example.data;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * class representing a cinema movie with its title, year, duration
 */
public class Movie implements Comparable<Movie> {
    /**
     * original title (mandatory)
     */
    private String title;

    /**
     * release year (mandatory)
     */
    private int year;

    /**
     * duration in minutes (not required)
     */
    private Integer duration;

    public Movie(String title, int year, Integer duration) {
        this.title = title;
        this.year = year;
        this.duration = duration;
    }

    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public Movie() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");
        sb.append(title)
                .append(" (")
                .append(year)
                .append(')');
        return sb.toString();
    }

    /**
     * Compare 2 movies by title first (ascending), then year (ascending).
     * This is the natural order of type Movie.
     * @param other the object to be compared.
     * @return integer <0 (this less than other), ==0 (this equals other),
     * >0 (this greater than oyher)
     */
    @Override
    public int compareTo(Movie other) {
        int cmp =  this.getTitle().compareTo(other.getTitle());
        // with same title (cmp=0), compare years
        if (cmp == 0) {
            cmp = this.getYear() - other.getYear();
        }
        return cmp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return (year == movie.year)
                && Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year);
    }
}
