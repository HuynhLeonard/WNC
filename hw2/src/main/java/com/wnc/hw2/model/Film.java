package com.wnc.hw2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "film")
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language_id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_language_id")
    private Language original_language_id;

    @Column(name = "rental_duration", nullable = false)
    private Long rentalDuration;

    @Column(name = "rental_rate", nullable = false, precision = 4)
    private BigDecimal rental_rate;

    @Column(name = "length")
    private Integer length;

    @Column(name = "replacement_cost", nullable = false, precision = 5)
    private BigDecimal replacement_cost;

    @Column(name = "rating", length = 5)
    @Convert(converter = RatingConverter.class)
    private Rating rating;

    @Column(name = "special_features", length = 54)
    private String special_features;

    @Column(name = "last_update", nullable = false)
    private String lastUpdate;
}