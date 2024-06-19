package sk.ukf.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf.cinema.entity.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

    List<Movie> findByDirector(String director);

    List<Movie> findByReleaseYear(int year);




}
