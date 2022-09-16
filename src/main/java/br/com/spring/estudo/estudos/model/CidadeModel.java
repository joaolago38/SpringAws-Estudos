package br.com.spring.estudo.estudos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "city")
@Getter
@Setter
public class CidadeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer city_id;
    @Column(name = "country_id")
    private Long countryId;
    @Column(name = "last_update")
    private Date lastUpdate;
}
