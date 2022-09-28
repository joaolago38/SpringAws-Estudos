package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class InventarioDto {

    @NotBlank
    private Integer inventoryId;
    @NotBlank
    private Integer filmId;
    @NotBlank
    private Integer storeId;
    @NotBlank
    private LocalDateTime lastUpdate;


}
