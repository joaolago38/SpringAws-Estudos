package br.com.spring.estudo.estudos.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PessoaDto {

    private Integer staffId;

    private String firstName;

    private String lastName;

    private Long addressId;

    private String email;

    private Integer storeId;

    private Boolean active;

    private String username;

    private String password;

    private LocalDateTime lastUpdate;

    private String picture; 
}
