package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
public class ClienteDto {

    @NotBlank
    private Long customerId;
    @NotBlank
    private Long storeId;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String email;
    @NotBlank
    private Long addressId;
    @NotBlank
    private Boolean activebool;
    @NotBlank
    private Date createDate;
    @NotBlank
    private Boolean active;



}
