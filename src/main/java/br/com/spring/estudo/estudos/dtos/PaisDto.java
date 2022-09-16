package br.com.spring.estudo.estudos.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class PaisDto {

    @NotBlank
    private Long  countryId;
    @NotBlank
    private String country;
    @NotBlank
    private Date lastUpdate;
}
