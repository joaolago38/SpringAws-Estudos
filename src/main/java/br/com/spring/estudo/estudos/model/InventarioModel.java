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
@Table(name = "inventory")
@Getter
@Setter
@Builder
public class InventarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator="inventory_inventory_id_seq")
    @SequenceGenerator(name="inventory_inventory_id_seq", sequenceName="inventory_inventory_id_seq",allocationSize = 1)
    private Integer inventoryId;
    @Column(name = "film_id")
    private Integer filmId;
    @Column(name = "store_id")
    private Integer storeId;
    @Column(name = "last_update")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime lastUpdate;

    public InventarioModel() {

    }

    public InventarioModel(Integer inventoryId, Integer filmId, Integer storeId, LocalDateTime lastUpdate) {
        this.inventoryId = inventoryId;
        this.filmId = filmId;
        this.storeId = storeId;
        this.lastUpdate = lastUpdate;
    }
}
