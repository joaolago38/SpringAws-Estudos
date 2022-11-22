package br.com.spring.estudo.estudos.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "address")
@Getter
@Setter
@Builder
public class EnderecoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator="address_address_id_seq")
    @SequenceGenerator(name="address_address_id_seq", sequenceName="address_address_id_seq",allocationSize = 1)
    private Integer addressId;
    @Column(name = "address")
    private String address;
    @Column(name = "address2")
    private String address2;
    @Column(name = "district")
    private String district;
    @Column(name = "city_id")
    private Long cityId;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "phone")
    private String phone;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public EnderecoModel() {

    }

    public EnderecoModel(Integer addressId, String address, String address2, String district, Long cityId, String postalCode, String phone, LocalDateTime lastUpdate) {
        this.addressId = addressId;
        this.address = address;
        this.address2 = address2;
        this.district = district;
        this.cityId = cityId;
        this.postalCode = postalCode;
        this.phone = phone;
        this.lastUpdate = lastUpdate;
    }
}
