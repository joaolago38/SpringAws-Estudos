package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PaisDto {


    private Integer  countryId;

    private String country;

    private Date lastUpdate;
}
