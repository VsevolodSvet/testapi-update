package com.kochmarev.testapiupdate.rest.controllers;

import com.kochmarev.testapiupdate.rest.data.dto.PaymentOrderDto;
import com.kochmarev.testapiupdate.rest.services.PaymentOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payment-orders", produces = {MediaType.APPLICATION_XML_VALUE})
@AllArgsConstructor
public class PaymentOrderController {

    private final PaymentOrderService paymentOrderService;

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public PaymentOrderDto updatePaymentOrder(@RequestBody PaymentOrderDto paymentOrderDto, @PathVariable Long id) throws Exception {
        return paymentOrderService.updatePaymentOrder(paymentOrderDto, id);
    }
}
