package org.example.data;

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
}
