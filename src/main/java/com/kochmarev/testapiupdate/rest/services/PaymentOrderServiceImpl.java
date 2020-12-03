package com.kochmarev.testapiupdate.rest.services;

import com.kochmarev.testapiupdate.rest.data.dto.PaymentOrderDto;
import com.kochmarev.testapiupdate.rest.data.entities.PaymentOrder;
import com.kochmarev.testapiupdate.rest.data.repositories.PaymentOrderRepository;
import com.kochmarev.testapiupdate.rest.exceptions.NoContentToUpdateException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Formatter;

@Service
@RequiredArgsConstructor
public class PaymentOrderServiceImpl implements PaymentOrderService {

    private final PaymentOrderRepository repository;
    private final ModelMapper mapper;

    @Override
    public PaymentOrderDto updatePaymentOrder(PaymentOrderDto dto, Long id) throws NoContentToUpdateException {
        PaymentOrder newOrder = mapper.map(dto, PaymentOrder.class);
        PaymentOrderDto result = repository.findById(id)
                .map(order -> {
                    fillPO(order, newOrder);
                    return mapper.map(repository.save(order), PaymentOrderDto.class);
                }).orElse(null);
        if (result == null) throw new NoContentToUpdateException(new Formatter().format("There is no records with id = %d", id).toString());
        return result;
    }

    // все, кроме id
    private void fillPO(PaymentOrder order, PaymentOrder newOrder) {
        order.setCommissionSum(newOrder.getCommissionSum());
        order.setDate(newOrder.getDate());
        order.setPaymentOrderNumber(newOrder.getPaymentOrderNumber());
        order.setPaymentPriority(newOrder.getPaymentPriority());
        order.setPaymentPriorityAuto(newOrder.getPaymentPriorityAuto());
        order.setPaymentPurpose(newOrder.getPaymentPurpose());
        order.setPaymentPurposeCode(newOrder.getPaymentPurposeCode());
        order.setPaymentSendType(newOrder.getPaymentSendType());
        order.setPaymentSum(newOrder.getPaymentSum());
        order.setTransKind(newOrder.getTransKind());
    }
}
