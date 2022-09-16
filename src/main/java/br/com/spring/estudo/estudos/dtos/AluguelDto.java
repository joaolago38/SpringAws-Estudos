package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;
@Getter
@Setter
public class AluguelDto {

    @NotBlank
    private Integer rental_id;
    @NotBlank
    private Date rentalDate;
    @NotBlank
    private Date returnDate;
    @NotBlank
    private Long customerId;
    @NotBlank
    private Long staffId;
    @NotBlank
    private Long inventoryId;
    @NotBlank
    private Date lastUpdate;
}
