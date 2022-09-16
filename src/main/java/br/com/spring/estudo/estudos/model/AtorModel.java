package br.com.spring.estudo.estudos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;




@Entity
@Table(name = "actor")
@Getter
@Setter
public class AtorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Integer actorId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "last_update")
    private Date lastUpdate;
}
