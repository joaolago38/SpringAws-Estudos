package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class FilmeCategoryDto {

    @NotBlank
    private Long filmId;
    @NotBlank
    private Long categoryId;
    @NotBlank
    private Date lastUpdate;



}
