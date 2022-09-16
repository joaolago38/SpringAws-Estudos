package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class InventarioDto {

    @NotBlank
    private Long inventoryId;
    @NotBlank
    private Long filmId;
    @NotBlank
    private Long storeId;


}
