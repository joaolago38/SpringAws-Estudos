package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmeDto {


    private  Integer filmId;

    private String title;

    private String description;

    private String releaseYear;

    private Long languageId;

    private String rentalDuration;

    private String rentalRate;

    private Integer length;

    private String replacementCost;


}
