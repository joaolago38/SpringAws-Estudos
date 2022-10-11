package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CidadeDto {


    private Integer cityId;

    private Long countryId;

    private Date lastUpdate;


}
