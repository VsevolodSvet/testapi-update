package com.kochmarev.testapiupdate.rest.services;

import com.kochmarev.testapiupdate.rest.data.dto.PaymentOrderDto;

public interface PaymentOrderService {
    PaymentOrderDto updatePaymentOrder(PaymentOrderDto dto, Long id) throws Exception;
}
