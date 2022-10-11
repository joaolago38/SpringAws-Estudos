package br.com.spring.estudo.estudos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;




@Entity
@Table(name = "actor")
@Getter
@Setter
public class AtorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator="actor_actor_id_seq")
    @SequenceGenerator(name="actor_actor_id_seq", sequenceName="actor_actor_id_seq",allocationSize = 1)
    private  Integer actorId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
