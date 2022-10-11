package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FilmeCategoryDto {


    private Integer filmId;

    private Integer categoryId;

    private LocalDateTime lastUpdate;



}
