package br.com.spring.estudo.estudos.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class PagamentoDto {

    @NotBlank
    private Long paymentId;
    @NotBlank
    private Long customerId;
    @NotBlank
    private Long staffId;
    @NotBlank
    private Long rentalId;
    @NotBlank
    private Long amount;
    @NotBlank
    private Date paymentDate;
}
