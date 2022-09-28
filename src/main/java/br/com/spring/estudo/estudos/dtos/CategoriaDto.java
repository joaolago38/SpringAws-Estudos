package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class CategoriaDto {
    @NotBlank
    private Integer categoryId;
    @NotBlank
    private Date lastUpdate;

}
