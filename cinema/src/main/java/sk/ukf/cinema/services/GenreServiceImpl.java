package sk.ukf.cinema.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import sk.ukf.cinema.entity.Genre;
import sk.ukf.cinema.repositories.GenreRepository;
import sk.ukf.cinema.repositories.MovieRepository;

import java.util.ArrayList;
import java.util.List;


@Service
public class GenreServiceImpl implements GenreService{

    private final GenreRepository genreRepository;

    private final MovieRepository movieRepository;

    public GenreServiceImpl(GenreRepository genreRepository, MovieRepository movieRepository) {
        this.genreRepository = genreRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    @Transactional
    public List<Genre> getAllMoviesByGenre(String genre) {
        List<Genre> genres = genreRepository.findByGenre(genre);
        return genres;
    }

    @Override
    public Genre getGenre(int id) {
        return genreRepository.findById(id).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre with " + id + " not found"));
    }

    @Override
    public Genre addGenre(Genre genre) {
        var dbGenre = genreRepository.findById(genre.getId());
        if (dbGenre.isEmpty()) {
            return genreRepository.save(genre);
        }
        genre.setMovies(dbGenre.get().getMovies());
        return genreRepository.save(genre);
    }

    @Override
    public List<Genre> getGenres() {
        return genreRepository.findAll();
    }

    @Override
    public void addGenreForMovie(int movieId, int genreId) {
        var genre = genreRepository.findById(genreId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre with " + genreId + " not found"));
        var movie = movieRepository.findById(movieId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with " + movieId + " not found"));
        var genres = movie.getGenres();
        if (genres == null) {
            genres = new ArrayList<>();
        }
        if (genres.contains(genre)) {
            return;
        }
        genres.add(genre);
        movie.setGenres(genres);
        movieRepository.save(movie);
    }

    @Override
    public void deleteGenreForMovie(int movieId, int genreId) {
        var genre = genreRepository.findById(genreId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Genre with " + genreId + " not found"));
        var movie = movieRepository.findById(movieId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie with " + movieId + " not found"));
        var genres = movie.getGenres();
        if (genres == null) {
            return;
        }
        genres.remove(genre);
        movie.setGenres(genres);
        movieRepository.save(movie);
    }

    @Override
    public boolean deleteMovie(int id) {
        var course = genreRepository.findById(id);
        if (course.isEmpty()) {
            return false;
        }
        genreRepository.delete(course.get());
        return true;
    }

}
