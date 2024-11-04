package com.wnc.filmserver.repository;

import com.wnc.filmserver.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    @Query("SELECT f from Film f join fetch f.language_id")
    List<Film> findFilmWithLanguage();
}
