package com.wnc.hw2.service;

import com.wnc.hw2.dto.request.FilmCreateRequest;
import com.wnc.hw2.dto.request.FilmUpdateRequest;
import com.wnc.hw2.exception.AppException;
import com.wnc.hw2.exception.ErrorCode;
import com.wnc.hw2.model.Film;
import com.wnc.hw2.model.Language;
import com.wnc.hw2.repository.FilmRepository;
import com.wnc.hw2.repository.LanguageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class FilmService {
    private final FilmRepository filmRepository;
    private final LanguageRepository languageRepository;

    // requirement 1: View all
    public List<Film> findAllFilm() {
        return filmRepository.findAll();
    }

    // requirement 2: View single film
    public Film findFilmById(Long id) {
        return filmRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.FILM_NOT_FOUND));
    }

    //// requirement 3: Create new film
    public Film createFilm(FilmCreateRequest film) {
        Film newFilm = new Film();

        newFilm.setTitle(film.getTitle());
        newFilm.setDescription(film.getDescription());
        Language language = languageRepository.findById(film.getLanguageId()).orElseThrow(() -> new AppException(ErrorCode.INVALID_LANGUAGE_ID));
        newFilm.setLanguage_id(language);
        Language originalLanguage = languageRepository.findById(film.getOriginalLanguageId()).orElse(null);
        newFilm.setOriginal_language_id(originalLanguage);
        newFilm.setRentalDuration(film.getRentalDuration());
        newFilm.setRental_rate(film.getRentalRate());
        newFilm.setLength(film.getLength());
        newFilm.setReplacement_cost(film.getReplacementCost());
        newFilm.setRating(film.getRating());
        newFilm.setSpecial_features(film.getSpecialFeatures().toString());
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        newFilm.setLastUpdate(now.format(formatter));

        return filmRepository.save(newFilm);
    }

    // requirement 4: Delete film
    public boolean deleteFilm(Long id) {
        Film deletedFilm = filmRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.FILM_NOT_FOUND));

        if(deletedFilm != null) {
            filmRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // requirement 5: Update film
    public Film updateFilm(FilmUpdateRequest film, Long id) {
        Film updatedFilm = filmRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.FILM_NOT_FOUND));

        if(film.getTitle() != null && !film.getTitle().isEmpty()){
            updatedFilm.setTitle(film.getTitle());
        }

        if(film.getDescription() != null && !film.getDescription().isEmpty()){
            updatedFilm.setDescription(film.getDescription());
        }

        if(film.getLanguageId() != null){
            Language language = languageRepository.findById(film.getLanguageId()).orElseThrow(() -> new AppException(ErrorCode.INVALID_LANGUAGE_ID));
            updatedFilm.setLanguage_id(language);
        }

        if(film.getOriginalLanguageId() != null){
            Language originalLanguage = languageRepository.findById(film.getOriginalLanguageId()).orElse(null);
            updatedFilm.setOriginal_language_id(originalLanguage);
        }

        if(film.getRentalDuration() != null){
            updatedFilm.setRentalDuration(film.getRentalDuration());
        }

        if(film.getRentalRate() != null){
            updatedFilm.setRental_rate(film.getRentalRate());
        }

        if(film.getLength() != null){
            updatedFilm.setLength(film.getLength());
        }

        if(film.getReplacementCost() != null){
            updatedFilm.setReplacement_cost(film.getReplacementCost());
        }

        if(film.getRating() != null){
            updatedFilm.setRating(film.getRating());
        }

        if(film.getSpecialFeatures() != null && !film.getSpecialFeatures().isEmpty()){
            updatedFilm.setSpecial_features(film.getSpecialFeatures().toString());
        }

        return filmRepository.save(updatedFilm);
    }
}
