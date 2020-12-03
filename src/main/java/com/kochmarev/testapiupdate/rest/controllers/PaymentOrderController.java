package com.kochmarev.testapiupdate.rest.controllers;

import com.kochmarev.testapiupdate.rest.data.dto.PaymentOrderDto;
import com.kochmarev.testapiupdate.rest.services.PaymentOrderService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment-orders")
@AllArgsConstructor
public class PaymentOrderController {

    private PaymentOrderService service;

    @SneakyThrows
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public PaymentOrderDto updatePaymentOrder(@RequestBody PaymentOrderDto newPaymentOrder, @PathVariable Long id) {
        return service.updatePaymentOrder(newPaymentOrder, id);
    }


}
