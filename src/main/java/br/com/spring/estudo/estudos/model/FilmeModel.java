package br.com.spring.estudo.estudos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "film")
@Getter
@Setter
public class FilmeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer filmId;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "release_year")
    private String releaseYear;
    @Column(name = "language_id")
    private Long languageId;
    @Column(name = "rental_duration")
    private String rentalDuration;
    @Column(name = "rental_rate")
    private String rentalRate;
    @Column(name = "length")
    private Integer length;
    @Column(name = "replacement_cost")
    private String replacementCost;
}
