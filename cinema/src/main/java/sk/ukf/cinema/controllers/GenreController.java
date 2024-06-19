package sk.ukf.cinema.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ukf.cinema.entity.Genre;
import sk.ukf.cinema.entity.Movie;
import sk.ukf.cinema.services.GenreService;

import java.util.List;

@RestController
@RequestMapping("/api")

public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genre/{id}")
    public Genre getGenre(@PathVariable int id) {
        return genreService.getGenre(id);
    }

    @GetMapping("/genre")
    public List<Genre> getGenres() {
        return genreService.getGenres();
    }

    @PostMapping("/genre")
    public ResponseEntity<Genre> addGenre(@Valid @RequestBody Genre genre) {
        return ResponseEntity.ok(genreService.addGenre(genre));
    }

    @GetMapping("/genre/genre/{genre}")
    public List<Genre> getAllMoviesByGenre(@PathVariable String genre) {
        return genreService.getAllMoviesByGenre(genre);
    }

    @PutMapping("/genre/{id}")
    public ResponseEntity<Genre> updateGenre(@PathVariable int id, @Valid @RequestBody Genre genre) {
        genre.setId(id);
        return ResponseEntity.ok(genreService.addGenre(genre));
    }

    @PostMapping("/genre/{genreId}/movie/{movieId}")
    public Genre addGenreForMovie(@PathVariable int movieId, @PathVariable int genreId){
        genreService.addGenreForMovie(movieId,genreId);
        return genreService.getGenre(genreId);
    }

    @DeleteMapping("/genre/{genreId}/movie/{movieId}")
        public Genre deleteGenreForMovie(@PathVariable int movieId, @PathVariable int genreId) {
        genreService.deleteGenreForMovie(movieId, genreId);
        return genreService.getGenre(genreId);
    }



    @DeleteMapping("/genre/{id}")
    public boolean deleteGenre(@PathVariable int id) {
        return genreService.deleteMovie(id);
    }

}
