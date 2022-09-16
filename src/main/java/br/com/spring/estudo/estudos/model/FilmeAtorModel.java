package br.com.spring.estudo.estudos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "film_actor")
@Getter
@Setter
public class FilmeAtorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer actorId;
    @Column(name = "film_id")
    private Integer filmId;
    @Column(name = "last_update")
    private Date lastUpdate;

}
