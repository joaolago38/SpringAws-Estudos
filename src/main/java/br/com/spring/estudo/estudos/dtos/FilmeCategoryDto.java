package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class FilmeCategoryDto {

    @NotBlank
    private Integer filmId;
    @NotBlank
    private Integer categoryId;
    @NotBlank
    private LocalDateTime lastUpdate;



}
