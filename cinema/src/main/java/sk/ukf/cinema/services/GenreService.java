package sk.ukf.cinema.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sk.ukf.cinema.entity.Genre;

import java.util.List;



public interface GenreService {

    @Transactional
    List<Genre> getAllMoviesByGenre(String genre);

    Genre getGenre(int id);

    Genre addGenre(Genre genre);

    List<Genre> getGenres();

    void addGenreForMovie(int movieId,int genreId);

    void deleteGenreForMovie(int movieId,int genreId);


    boolean deleteMovie(int id);
}
