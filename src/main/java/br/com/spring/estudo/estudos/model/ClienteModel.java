package br.com.spring.estudo.estudos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "customer")
@Getter
@Setter
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer customerId;
    @Column(name = "store_id")
    private Integer storeId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "address_id")
    private Long addressId;
    @Column(name = "activebool")
    private Boolean activebool;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @Column(name = "active")
    private Boolean active;

}
