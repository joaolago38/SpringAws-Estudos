package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LanguagemDto {


    private  Integer languageId;

    private String name;

    private Date lastUpdate;
}
