package com.wnc.hw2.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "language")
public class Language {
    @Id
    @Column(name = "language_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "last_update", nullable = false)
    private String lastUpdate;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "language_id")
//    @Fetch(FetchMode.SUBSELECT)
//    private List<Film> language_id;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "original_language_id")
//    @Fetch(FetchMode.SUBSELECT)
//    private List<Film> original_language_id;

}