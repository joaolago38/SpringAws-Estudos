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
@Table(name = "store")
@Getter
@Setter
@Builder
public class ArmazenamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator="store_store_id_seq")
    @SequenceGenerator(name="store_store_id_seq", sequenceName="store_store_id_seq",allocationSize = 1)
    private Integer storeId;
    @Column(name = "manager_staff_id")
    private Integer managerStaffId;
    @Column(name = "address_id")
    private Long addressId;
    @Column(name = "lastUpdate")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime lastUpdate;

    public ArmazenamentoModel() {

    }

    public ArmazenamentoModel(Integer storeId, Integer managerStaffId, Long addressId, LocalDateTime lastUpdate) {
        this.storeId = storeId;
        this.managerStaffId = managerStaffId;
        this.addressId = addressId;

        this.lastUpdate = lastUpdate;
    }
}
