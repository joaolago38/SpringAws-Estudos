package br.com.spring.estudo.estudos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "country")
@Getter
@Setter
public class PaisModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  countryId;
    @Column(name = "country")
    private String country;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
