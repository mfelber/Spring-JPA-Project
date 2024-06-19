package sk.ukf.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.ukf.cinema.entity.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre,Integer> {
    // custom methods
    List<Genre> findByGenre(String genre);
}
