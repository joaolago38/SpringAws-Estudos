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
@Setter
@Getter
@Builder
@Table(name = "category")
public class CategoriaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator="category_category_id_seq")
    @SequenceGenerator(name="category_category_id_seq", sequenceName="category_category_id_seq",allocationSize = 1)
    private Integer categoryId;
    @Column(name = "last_update")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime lastUpdate;

    public CategoriaModel() {

    }

    public CategoriaModel(Integer categoryId, LocalDateTime lastUpdate) {
        this.categoryId = categoryId;
        this.lastUpdate = lastUpdate;
    }
}
