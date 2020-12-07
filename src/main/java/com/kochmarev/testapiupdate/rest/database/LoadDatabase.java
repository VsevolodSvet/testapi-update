package com.kochmarev.testapiupdate.rest.database;

import com.kochmarev.testapiupdate.rest.data.entities.PaymentOrder;
import com.kochmarev.testapiupdate.rest.data.repositories.PaymentOrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	private static final List<PaymentOrder> PO_LIST = new ArrayList<>();

	private void initPaymentOrders() {
		Date dateForFirstPO = new GregorianCalendar(2020, Calendar.DECEMBER, 4).getTime();
		PO_LIST.add(PaymentOrder.builder()
				.id(1L)
				.commissionSum(BigDecimal.valueOf(-100L))
				.date(dateForFirstPO)
				.paymentOrderNumber("1234")
				.paymentPriority("0")
				.paymentPriorityAuto("y")
				.paymentPurpose("before_update_1")
				.paymentPurposeCode("0")
				.paymentSendType("z")
				.paymentSum(BigDecimal.ZERO)
				.transKind("z")
				.build()
		);
		PO_LIST.add(PaymentOrder.builder()
				.id(2L)
				.commissionSum(BigDecimal.valueOf(-200L))
				.date(new Date())
				.paymentOrderNumber("4321")
				.paymentPriority("0")
				.paymentPriorityAuto("y")
				.paymentPurpose("before_update_2")
				.paymentPurposeCode("0")
				.paymentSendType("z")
				.paymentSum(BigDecimal.ZERO)
				.transKind("z")
				.build()
		);
	}

	@Bean
    CommandLineRunner initDatabase(PaymentOrderRepository repository) {
		initPaymentOrders();
		return args -> {
			for (PaymentOrder paymentOrder : PO_LIST) {
				log.info("Preloading " + repository.save(paymentOrder));
			}
			repository.findAll().forEach(paymentOrder -> log.info("Preloaded PO with id = " + paymentOrder.getId()));
		};
	}
}
