package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class EnderecoDto {

    @NotBlank
    private Integer addressId;
    @NotBlank
    private String address;
    @NotBlank
    private String address2;
    @NotBlank
    private String district;
    @NotBlank
    private Long cityId;
    @NotBlank
    private String postalCode;
    @NotBlank
    private String phone;

}
