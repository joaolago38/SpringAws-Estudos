package br.com.spring.estudo.estudos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;



@Entity
@Table(name = "store")
@Getter
@Setter
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
    private LocalDateTime lastUpdate;
}
