package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ArmazenamentoDto {

    @NotBlank
    private Integer storeId;
    @NotBlank
    private Long managerStaffId;
    @NotBlank
    private Long addressId;
    @NotBlank
    private Long lastUpdate;

}
