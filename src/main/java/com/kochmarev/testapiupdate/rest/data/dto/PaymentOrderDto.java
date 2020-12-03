package com.kochmarev.testapiupdate.rest.data.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@JacksonXmlRootElement(localName = "paymentOrderDto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentOrderDto {

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