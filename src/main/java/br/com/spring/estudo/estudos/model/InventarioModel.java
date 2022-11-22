package br.com.spring.estudo.estudos.model;

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
    private LocalDateTime lastUpdate;
}
