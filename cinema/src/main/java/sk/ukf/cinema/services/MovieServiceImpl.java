package sk.ukf.cinema.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import sk.ukf.cinema.entity.Movie;
import sk.ukf.cinema.repositories.GenreRepository;
import sk.ukf.cinema.repositories.MovieRepository;

import java.util.List;

@Service

public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie getMovie(int id) {
        return movieRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Movie with " + id + " not found"
        ));
    }

    @Override
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }


    @Override
    @Transactional
    public List<Movie> getAllMoviesByDirector(String director) {
        List<Movie> movies = movieRepository.findByDirector(director);
        return movies;
    }

    @Override
    @Transactional
    public List<Movie> getAllMoviesByYear(int year) {
        List<Movie> movies = movieRepository.findByReleaseYear(year);
        return movies;
    }





    @Override
    public Movie addMovie(Movie movie) {
        var dbMovie = movieRepository.findById(movie.getId());

        if (dbMovie.isEmpty()) {
            return movieRepository.save(movie);
        }
        movie.setGenres(dbMovie.get().getGenres());
        return movieRepository.save(movie);
    }

    @Override
    public boolean deleteMovie(int id) {
        var movie = movieRepository.findById(id);
        if (movie.isEmpty()) {
            return false;
        }
        movieRepository.delete(movie.get());
        return true;
    }
}
