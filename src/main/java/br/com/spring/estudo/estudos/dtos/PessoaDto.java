package br.com.spring.estudo.estudos.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class PessoaDto {
    @NotBlank
    private Long staff_id;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private Long addressId;
    @NotBlank
    private String email;
    @NotBlank
    private String storeId;
    @NotBlank
    private Boolean active;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private Date lastUpdate;
    @NotBlank
    private String picture; 
}
