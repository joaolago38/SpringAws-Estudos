package br.com.spring.estudo.estudos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "staff")
@Getter
@Setter
public class PessoaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer staffId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "address_id")
    private Long addressId;
    @Column(name = "email")
    private String email;
    @Column(name = "store_id")
    private String storeId;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "last_update")
    private Date lastUpdate;
    @Column(name = "picture")
    private String picture;
}
