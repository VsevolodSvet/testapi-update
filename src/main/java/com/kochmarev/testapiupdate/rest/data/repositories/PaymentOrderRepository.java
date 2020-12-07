package com.kochmarev.testapiupdate.rest.data.repositories;

import com.kochmarev.testapiupdate.rest.data.entities.PaymentOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentOrderRepository extends CrudRepository<PaymentOrder, Long>{
    PaymentOrder findByPaymentOrderNumber(String s);
}
