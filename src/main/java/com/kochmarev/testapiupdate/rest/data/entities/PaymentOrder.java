package com.kochmarev.testapiupdate.rest.data.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String transKind;
    private String paymentOrderNumber;
    private Date date;
    private BigDecimal paymentSum;
    private BigDecimal commissionSum;
    private String paymentPurpose;
    private String paymentPriority;
    private String paymentPriorityAuto;
    private String paymentSendType;
    private String paymentPurposeCode;
}
