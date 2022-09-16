package br.com.spring.estudo.estudos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "inventory")
@Getter
@Setter
public class InventarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer inventoryId;
    @Column(name = "film_id")
    private Long filmId;
    @Column(name = "store_id")
    private Long storeId;
    @Column(name = "last_update")
    private Date lastUpdate;
}
