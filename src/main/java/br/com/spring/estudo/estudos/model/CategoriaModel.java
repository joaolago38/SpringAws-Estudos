package br.com.spring.estudo.estudos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "category")
@Getter
@Setter
public class CategoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer categoryId;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
