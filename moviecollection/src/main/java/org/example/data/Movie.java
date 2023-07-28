package org.example.data;

import lombok.*;

import java.util.*;

/**
 * class representing a cinema movie with its title, year, duration
 */
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(of={"title", "year"})
@EqualsAndHashCode(of={"title", "year"})
public class Movie implements Comparable<Movie> {
    /**
     * original title (mandatory)
     */
    @NonNull
    private String title;

    /**
     * release year (mandatory)
     */
    @NonNull
    private int year;

    /**
     * duration in minutes (not required)
     */
    private Integer duration;

    /**
     * genres (0, 1 or several)
     */
    @Builder.Default // @Singular
    private Set<String> genres = new HashSet<>();

    /**
     * director (optional)
     */
    private Person director;

    /**
     * actors (0, 1 or several)
     */
    @Builder.Default
    private List<Person> actors = new ArrayList<>();

//    public Movie(String title, int year, Integer duration) {
//        this.title = title;
//        this.year = year;
//        this.duration = duration;
//    }
//
//    public Movie(String title, int year) {
//        this.title = title;
//        this.year = year;
//    }
//
//    public Movie() {
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public int getYear() {
//        return year;
//    }
//
//    public void setYear(int year) {
//        this.year = year;
//    }
//
//    public Integer getDuration() {
//        return duration;
//    }
//
//    public void setDuration(Integer duration) {
//        this.duration = duration;
//    }
//
//    @Override
//    public String toString() {
//        final StringBuilder sb = new StringBuilder("");
//        sb.append(title)
//                .append(" (")
//                .append(year)
//                .append(')');
//        return sb.toString();
//    }

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

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Movie movie = (Movie) o;
//        return (year == movie.year)
//                && Objects.equals(title, movie.title);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(title, year);
//    }
}
