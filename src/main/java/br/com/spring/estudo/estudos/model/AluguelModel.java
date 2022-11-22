package br.com.spring.estudo.estudos.model;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "rental")
@Getter
@Setter
@Builder
public class AluguelModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_rental_id_seq")
    @SequenceGenerator(name = "rental_rental_id_seq", sequenceName = "rental_rental_id_seq", allocationSize = 1)
    private Integer rentalId;
    @Column(name = "rental_date")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime rentalDate;
    @Column(name = "return_date")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime returnDate;
    @Column(name = "customer_id")
    private Integer customerId;
    @Column(name = "staff_id")
    private Integer staffId;
    @Column(name = "inventory_id")
    private Integer inventoryId;
    @Column(name = "last_update")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime lastUpdate;


    public AluguelModel(Integer rentalId, LocalDateTime rentalDate, LocalDateTime returnDate, Integer customerId, Integer staffId, Integer inventoryId, LocalDateTime lastUpdate) {
        this.rentalId = rentalId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.customerId = customerId;
        this.staffId = staffId;
        this.inventoryId = inventoryId;
        this.lastUpdate = lastUpdate;
    }

    public AluguelModel() {

    }


}
