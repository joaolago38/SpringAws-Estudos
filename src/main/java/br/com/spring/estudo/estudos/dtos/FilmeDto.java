package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class FilmeDto {

    @NotBlank
    private  Integer filmId;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String releaseYear;
    @NotBlank
    private Long languageId;
    @NotBlank
    private String rentalDuration;
    @NotBlank
    private String rentalRate;
    @NotBlank
    private Integer length;
    @NotBlank
    private String replacementCost;


}
