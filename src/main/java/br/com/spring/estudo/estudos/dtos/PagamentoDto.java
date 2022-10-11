package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PagamentoDto {


    private Integer paymentId;

    private Long customerId;

    private Long staffId;

    private Long rentalId;

    private Long amount;

    private Date paymentDate;
}
