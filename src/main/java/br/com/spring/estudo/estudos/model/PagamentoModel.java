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
@Table(name = "payment")
@Getter
@Setter
@Builder
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
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime paymentDate;

    public PagamentoModel() {

    }

    public PagamentoModel(Integer paymenId, Long customerId, Long staffId, Long rentalId, Long amount, LocalDateTime paymentDate) {
        this.paymenId = paymenId;
        this.customerId = customerId;
        this.staffId = staffId;
        this.rentalId = rentalId;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }
}
