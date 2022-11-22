package br.com.spring.estudo.estudos.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "country")
@Getter
@Setter
@Builder
public class PaisModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator="country_country_id_seq")
    @SequenceGenerator(name="country_country_id_seq", sequenceName="country_country_id_seq",allocationSize = 1)
    private Integer  countryId;
    @Column(name = "country")
    private String country;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public PaisModel() {

    }
}
