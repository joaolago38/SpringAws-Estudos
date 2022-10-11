package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FilmeActorDto {

    private Integer actorId;

    private Integer filmId;

    private LocalDateTime lastUpdate;
}
