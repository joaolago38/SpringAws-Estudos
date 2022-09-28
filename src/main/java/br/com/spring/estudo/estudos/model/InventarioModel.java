package br.com.spring.estudo.estudos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "inventory")
@Getter
@Setter
public class InventarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer inventoryId;
    @Column(name = "film_id")
    private Integer filmId;
    @Column(name = "store_id")
    private Integer storeId;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
