package br.com.spring.estudo.estudos.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "film")
@Getter
@Setter
@Builder
public class FilmeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator="film_film_id_seq")
    @SequenceGenerator(name="film_film_id_seq", sequenceName="film_film_id_seq",allocationSize = 1)
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
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private String rentalRate;
    @Column(name = "length")
    private Integer length;
    @Column(name = "replacement_cost")
    private String replacementCost;

    public FilmeModel() {

    }

    public FilmeModel(Integer filmId, String title, String description, String releaseYear, Long languageId, String rentalDuration, String rentalRate, Integer length, String replacementCost) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.languageId = languageId;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
    }
}
