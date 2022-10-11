package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AtorDto {

    private Integer actorId;

    private String firstName;

    private String lastName;

    private Date lastUpdate;


}
