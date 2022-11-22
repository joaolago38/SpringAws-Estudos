package br.com.spring.estudo.estudos.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "staff")
@Getter
@Setter
@Builder
public class PessoaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator="staff_staff_id_seq")
    @SequenceGenerator(name="staff_staff_id_seq", sequenceName="staff_staff_id_seq",allocationSize = 1)
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
    private Integer storeId;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
    @Column(name = "picture")
    private String picture;

    public PessoaModel() {

    }

    public PessoaModel(Integer staffId, String firstName, String lastName, Long addressId, String email, Integer storeId, Boolean active, String username, String password, LocalDateTime lastUpdate, String picture) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressId = addressId;
        this.email = email;
        this.storeId = storeId;
        this.active = active;
        this.username = username;
        this.password = password;
        this.lastUpdate = lastUpdate;
        this.picture = picture;
    }
}
