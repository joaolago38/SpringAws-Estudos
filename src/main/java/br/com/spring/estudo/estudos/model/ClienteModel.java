package br.com.spring.estudo.estudos.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "customer")
@Getter
@Setter
@Builder
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator="customer_customer_id_seq")
    @SequenceGenerator(name="customer_customer_id_seq", sequenceName="customer_customer_id_seq",allocationSize = 1)
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
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createDate;
    @Column(name = "active")
    private Boolean active;

    public ClienteModel() {

    }

    public ClienteModel(Integer customerId, Integer storeId, String firstName, String lastName, String email, Long addressId, Boolean activebool, LocalDateTime createDate, Boolean active) {
        this.customerId = customerId;
        this.storeId = storeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressId = addressId;
        this.activebool = activebool;
        this.createDate = createDate;
        this.active = active;
    }
}
