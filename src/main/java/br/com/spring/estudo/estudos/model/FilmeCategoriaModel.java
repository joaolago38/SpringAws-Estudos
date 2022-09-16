package br.com.spring.estudo.estudos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "film_category")
@Getter
@Setter
public class FilmeCategoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer filmId;
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "last_update")
    private Date lastUpdate;
}
