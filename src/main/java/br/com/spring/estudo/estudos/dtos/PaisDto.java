package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class PaisDto {

    @NotBlank
    private Integer  countryId;
    @NotBlank
    private String country;
    @NotBlank
    private Date lastUpdate;
}
