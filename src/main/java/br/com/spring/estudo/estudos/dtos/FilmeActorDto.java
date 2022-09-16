package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class FilmeActorDto {
    @NotBlank
    private Integer actorId;
    @NotBlank
    private Integer filmId;
    @NotBlank
    private Date lastUpdate;
}
