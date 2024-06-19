package sk.ukf.cinema.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.ukf.cinema.entity.Movie;
import sk.ukf.cinema.repositories.MovieRepository;
import sk.ukf.cinema.services.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {


    private final MovieService movieService;

    private final MovieRepository movieRepository;
    @Autowired
    public MovieController(MovieService movieService, MovieRepository movieRepository) {
        this.movieService = movieService;
        this.movieRepository = movieRepository;
    }

    @GetMapping("/movie/{id}")
    public Movie getMovie(@PathVariable int id) {
        return movieService.getMovie(id);
    }

    @GetMapping("/movie")
    public List<Movie> getMovies() {
        return movieService.getMovies();
    }

    @PostMapping("/movie")
    public ResponseEntity<Movie> addMovie(@Valid @RequestBody Movie movie) {
        return ResponseEntity.ok(movieService.addMovie(movie));
    }

    @GetMapping("/movie/director/{director}")
    public List<Movie> getAllMoviesByDirector(@PathVariable String director) {
        return movieService.getAllMoviesByDirector(director);
    }

    @GetMapping("/movie/year/{year}")
    public List<Movie> getAllMoviesByYear(@PathVariable int year) {
        return movieService.getAllMoviesByYear(year);
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable int id , @Valid @RequestBody Movie movie) {
        movie.setId(id);
        return ResponseEntity.ok(movieService.addMovie(movie));

    }

    @DeleteMapping("/movie/{id}")
    public boolean deleteStudent(@PathVariable int id) {
        return movieService.deleteMovie(id);
    }


}
