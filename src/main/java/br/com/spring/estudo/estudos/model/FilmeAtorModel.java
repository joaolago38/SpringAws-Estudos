package br.com.spring.estudo.estudos.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
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
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime lastUpdate;

    public FilmeAtorModel() {

    }

    public FilmeAtorModel(Integer actorId, Integer filmId, LocalDateTime lastUpdate) {
        this.actorId = actorId;
        this.filmId = filmId;
        this.lastUpdate = lastUpdate;
    }
}
