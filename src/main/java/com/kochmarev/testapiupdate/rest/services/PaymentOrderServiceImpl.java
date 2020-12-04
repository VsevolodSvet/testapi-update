package com.kochmarev.testapiupdate.rest.services;

import com.kochmarev.testapiupdate.rest.data.dto.PaymentOrderDto;
import com.kochmarev.testapiupdate.rest.data.entities.PaymentOrder;
import com.kochmarev.testapiupdate.rest.data.repositories.PaymentOrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PaymentOrderServiceImpl implements PaymentOrderService {

    private final PaymentOrderRepository paymentOrderRepository;

    private final ModelMapper mapper;

    @Override
    public PaymentOrderDto updatePaymentOrder(PaymentOrderDto paymentOrderDto, Long id) {
        PaymentOrder paymentOrder = paymentOrderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity no found"));
        updateEntityFromDto(paymentOrder, paymentOrderDto);
        return mapper.map(paymentOrderRepository.save(paymentOrder), PaymentOrderDto.class);
    }

    private void updateEntityFromDto(PaymentOrder paymentOrder, PaymentOrderDto paymentOrderDto) {
        paymentOrder.setCommissionSum(paymentOrderDto.getCommissionSum());
        paymentOrder.setDate(paymentOrderDto.getDate());
        paymentOrder.setPaymentOrderNumber(paymentOrderDto.getPaymentOrderNumber());
        paymentOrder.setPaymentPriority(paymentOrderDto.getPaymentPriority());
        paymentOrder.setPaymentPriorityAuto(paymentOrderDto.getPaymentPriorityAuto());
        paymentOrder.setPaymentPurpose(paymentOrderDto.getPaymentPurpose());
        paymentOrder.setPaymentPurposeCode(paymentOrderDto.getPaymentPurposeCode());
        paymentOrder.setPaymentSendType(paymentOrderDto.getPaymentSendType());
        paymentOrder.setPaymentSum(paymentOrderDto.getPaymentSum());
        paymentOrder.setTransKind(paymentOrderDto.getTransKind());
    }
}
