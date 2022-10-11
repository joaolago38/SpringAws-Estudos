package br.com.spring.estudo.estudos.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "rental")
@Getter
@Setter
public class AluguelModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator="rental_rental_id_seq")
    @SequenceGenerator(name="rental_rental_id_seq", sequenceName="rental_rental_id_seq",allocationSize = 1)
    private Integer rentalId;
    @Column(name = "rental_date")
    private LocalDateTime rentalDate;
    @Column(name = "return_date")
    private LocalDateTime returnDate;
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "staff_id")
    private Integer staffId;
    @Column(name = "inventory_id")
    private Integer inventoryId;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;


}
