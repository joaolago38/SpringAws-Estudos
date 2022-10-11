package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ClienteDto {


    private Integer customerId;

    private Long storeId;

    private String firstName;

    private String lastName;

    private String email;

    private Long addressId;

    private Boolean activebool;

    private Date createDate;

    private Boolean active;


}
