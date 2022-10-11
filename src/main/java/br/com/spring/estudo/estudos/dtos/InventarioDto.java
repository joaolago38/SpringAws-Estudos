package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InventarioDto {


    private Integer inventoryId;

    private Integer filmId;

    private Integer storeId;

    private LocalDateTime lastUpdate;


}
