package br.com.spring.estudo.estudos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "language")
@Getter
@Setter
public class LinguagemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer languageId;
    @Column(name = "name")
    private String name;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

}
