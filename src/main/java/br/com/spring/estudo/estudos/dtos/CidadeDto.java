package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class CidadeDto {

    @NotBlank
    private Integer cityId;
    @NotBlank
    private Long countryId;
    @NotBlank
    private Date lastUpdate;


}
