package br.com.spring.estudo.estudos.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "film_actor")
@Getter
@Setter
@Builder
public class FilmeAtorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer actorId;
    @Column(name = "film_id")
    private Integer filmId;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public FilmeAtorModel() {

    }
}
