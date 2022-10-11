package br.com.spring.estudo.estudos.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "payment")
@Getter
@Setter
public class PagamentoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator="payment_payment_id_seq")
    @SequenceGenerator(name="payment_payment_id_seq", sequenceName="payment_payment_id_seqactor_actor_id_seq",allocationSize = 1)
    private Integer paymenId;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "staff_id")
    private Long staffId;
    @Column(name = "rental_id")
    private Long rentalId;
    @Column(name = "amount")
    private Long amount;
    @Column(name = "payment_date")
    private LocalDateTime paymentDate;
}
