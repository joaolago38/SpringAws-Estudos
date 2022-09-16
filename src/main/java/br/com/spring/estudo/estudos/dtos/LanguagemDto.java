package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class LanguagemDto {

    @NotBlank
    private  Long languageId;
    @NotBlank
    private String name;
    @NotBlank
    private Date lastUpdate;
}
