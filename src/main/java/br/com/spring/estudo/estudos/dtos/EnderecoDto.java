package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoDto {


    private Integer addressId;

    private String address;

    private String address2;

    private String district;

    private Long cityId;

    private String postalCode;

    private String phone;

}
