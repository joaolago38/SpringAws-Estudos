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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer storeId;
    @Column(name = "manager_staff_id")
    private Long managerStaffId;
    @Column(name = "address_id")
    private Long addressId;
    @Column(name = "lastUpdate")
    private LocalDateTime lastUpdate;
}
