package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CategoriaDto {

    private Integer categoryId;

    private Date lastUpdate;

}
