package com.wnc.filmserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "film")
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Integer year;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language_id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_language_id")
    private Language original_language_id;

    @Column(name = "rental_duration", nullable = false)
    private Integer rentalDuration;

    @Column(name = "rental_rate", nullable = false, precision = 4)
    private BigDecimal rental_rate;

    @Column(name = "length")
    private Integer length;

    @Column(name = "replacement_cost", nullable = false, precision = 5)
    private BigDecimal replacement_cost;

    @Column(name = "rating", length = 5)
    //@Convert(converter = RatingConverter.class)
    private String rating;

    @Column(name = "special_features", length = 54)
    private String special_features;

    @Column(name = "last_update", nullable = false)
    private String lastUpdate;

    @JsonProperty("language")
    public String getLanguageNameBYId() {
        return language_id != null ? language_id.getName() : null;
    }

    @JsonProperty("original_language")
    public String getOriginalLanguageId() {
        return original_language_id != null ? original_language_id.getName() : null;
    }
}