package br.com.spring.estudo.estudos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "city")
@Getter
@Setter
public class CidadeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cityid;
    @Column(name = "country_id")
    private Integer countryId;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
