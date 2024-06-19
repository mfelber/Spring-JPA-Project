package sk.ukf.cinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "film")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private int id;

    @NotEmpty
    @Column(name = "movie_name")
    @Size(min = 1)
    private String movieName;


    @NotEmpty
    @Column(name = "language")

    @Size(max = 5,min = 2)
    private String language;


    @Column(name = "length")
    @NotNull
    @Min(value = 1, message = "dlzka filmy musi byt vacsia ako 1")
    private int length;


    @NotEmpty
    @Column(name = "director")
    @Size(max = 255,min = 1)
    private String director;


    @NotEmpty
    @Column(name = "country")
    @Size(max = 255,min = 2)
    private String country;


    @Column(name = "release_year")
    @NotNull
    @Min(value = 1990, message = "rok musi byt vacsi alebo rovny 1990")
    private int releaseYear;


    @Column(name = "rating")
    @NotNull
    @Min(value = 1, message = "hodnotenie musi byt vacsie rovne 1")
    private int rating;


    @Column(name = "box_office")
    @NotNull
    @Min(value = 0, message = "boxoffice musi byt vacsi alebo 1")
    private int boxOffice;

    @NotEmpty
    @Column(name = "production_company")
    @Size(max = 500,min = 1)
    private String productionCompany;

    @NotEmpty
    @Column(name = "award")
    @Size(max = 500,min = 1)
    private String award;


    @Column(name = "budget")
    @NotNull
    @Min(value = 1, message = "budget musi byt vacsie rovne 1")
    private int budget;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH,CascadeType.REFRESH})
    @JoinTable(name = "film_has_zaner", joinColumns = @JoinColumn(name = "film_id"), inverseJoinColumns = @JoinColumn(name = "zaner_id"))
    @JsonIgnoreProperties("movies")
    private List<Genre> genres;

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public int getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(int boxOffice) {
        this.boxOffice = boxOffice;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int lenght) {
        this.length = lenght;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
