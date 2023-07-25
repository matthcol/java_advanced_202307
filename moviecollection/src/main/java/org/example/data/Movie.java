package org.example.data;

import java.util.StringJoiner;

/**
 * class representing a cinema movie with its title, year, duration
 */
public class Movie {
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


//    @Override
//    public String toString() {
//        return new StringJoiner(", ", Movie.class.getSimpleName() + "[", "]")
//                .add("title='" + title + "'")
//                .add("year=" + year)
//                .toString();
//    }
}
