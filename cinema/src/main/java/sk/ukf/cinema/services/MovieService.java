package sk.ukf.cinema.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.ukf.cinema.entity.Movie;

import java.util.List;


public interface MovieService {

    Movie getMovie(int id);

    List<Movie> getMovies();

    @Transactional
    List<Movie> getAllMoviesByDirector(String director);

    @Transactional
    List<Movie> getAllMoviesByYear(int year);

    Movie addMovie(Movie movie);

    boolean deleteMovie(int id);


}
