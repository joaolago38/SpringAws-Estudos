package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArmazenamentoDto {


    private Integer storeId;

    private Integer managerStaffId;

    private Long addressId;

    private LocalDateTime lastUpdate;

}
