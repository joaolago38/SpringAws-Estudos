package br.com.spring.estudo.estudos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Setter
@Getter
@Table(name = "category")
public class CategoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator="category_category_id_seq")
    @SequenceGenerator(name="category_category_id_seq", sequenceName="category_category_id_seq",allocationSize = 1)
    private Integer categoryId;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
