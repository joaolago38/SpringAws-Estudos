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
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime lastUpdate;

    public PaisModel() {

    }

    public PaisModel(Integer countryId, String country, LocalDateTime lastUpdate) {
        this.countryId = countryId;
        this.country = country;
        this.lastUpdate = lastUpdate;
    }
}
