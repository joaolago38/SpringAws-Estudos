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
@Table(name = "city")
@Getter
@Setter
@Builder
public class CidadeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator="city_city_id_seq")
    @SequenceGenerator(name="city_city_id_seq", sequenceName="city_city_id_seq",allocationSize = 1)
    private Integer cityid;
    @Column(name = "country_id")
    private Integer countryId;
    @Column(name = "last_update")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime lastUpdate;

    public CidadeModel() {

    }

    public CidadeModel(Integer cityid, Integer countryId, LocalDateTime lastUpdate) {
        this.cityid = cityid;
        this.countryId = countryId;
        this.lastUpdate = lastUpdate;
    }
}
