package com.kochmarev.testapiupdate.rest.controllers;

import com.kochmarev.testapiupdate.rest.data.entities.PaymentOrder;
import com.kochmarev.testapiupdate.rest.data.repositories.PaymentOrderRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.kochmarev.testapiupdate.utils.CommonHelper.readResourcesFileContentAsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class PaymentOrderControllerTest {

    @Autowired
    private PaymentOrderController paymentOrderController;

    @Autowired
    private PaymentOrderRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void checkController() {
        assertNotNull(paymentOrderController);
    }

    @Test
    @SneakyThrows
    public void updatePaymentOrder_positive() {

        PaymentOrder oldPaymentOrder = repository.findByPaymentOrderNumber("1234");
        Date oldDateToCheck = new GregorianCalendar(2020, Calendar.DECEMBER, 4).getTime();

        assertEquals(oldPaymentOrder.getId().longValue(), 1L);
        assertEquals(oldPaymentOrder.getCommissionSum().compareTo(BigDecimal.valueOf(-100L)), 0);
        assertEquals(oldPaymentOrder.getDate().compareTo(oldDateToCheck), 0);
        assertEquals(oldPaymentOrder.getPaymentPriority(), "0");
        assertEquals(oldPaymentOrder.getPaymentPriorityAuto(), "y");
        assertEquals(oldPaymentOrder.getPaymentPurpose(), "before_update_1");
        assertEquals(oldPaymentOrder.getPaymentPurposeCode(), "0");
        assertEquals(oldPaymentOrder.getPaymentSendType(), "z");
        assertEquals(oldPaymentOrder.getPaymentSum().compareTo(BigDecimal.ZERO), 0);
        assertEquals(oldPaymentOrder.getTransKind(), "z");

        mockMvc.perform(put("/payment-orders/{id}", 1)
                .content(readResourcesFileContentAsString("test_payment_order.xml"))
                .contentType(MediaType.APPLICATION_XML)
        ).andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(xpath("/paymentOrderDto/transKind").string("kt"))
                .andExpect(xpath("/paymentOrderDto/paymentOrderNumber").string("1234567890"))
                .andExpect(xpath("/paymentOrderDto/date").string("2020-12-03T09:28:15.637+00:00"))
                .andExpect(xpath("/paymentOrderDto/paymentSum").string("1111.11"))
                .andExpect(xpath("/paymentOrderDto/commissionSum").string("22.22"))
                .andExpect(xpath("/paymentOrderDto/paymentPurpose").string("test_purpose"))
                .andExpect(xpath("/paymentOrderDto/paymentPriority").string("5"))
                .andExpect(xpath("/paymentOrderDto/paymentPriorityAuto").string("n"))
                .andExpect(xpath("/paymentOrderDto/paymentSendType").string("s"))
                .andExpect(xpath("/paymentOrderDto/paymentPurposeCode").string("1"));
    }

    @Test
    @SneakyThrows
    public void updatePaymentOrder_negative() {
        mockMvc.perform(put("/payment-orders/{id}", 3)
                .content(readResourcesFileContentAsString("test_payment_order.xml"))
                .contentType(MediaType.APPLICATION_XML)
        ).andDo(print())
                .andExpect(status().isNotFound());
    }
}