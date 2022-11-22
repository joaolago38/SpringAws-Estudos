package br.com.spring.estudo.estudos.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "language")
@Getter
@Setter
@Builder
public class LinguagemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator="language_language_id_seq")
    @SequenceGenerator(name="language_language_id_seq", sequenceName="language_language_id_seq",allocationSize = 1)
    private  Integer languageId;
    @Column(name = "name")
    private String name;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public LinguagemModel() {

    }
}
