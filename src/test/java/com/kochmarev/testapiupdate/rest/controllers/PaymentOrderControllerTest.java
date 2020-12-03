package com.kochmarev.testapiupdate.rest.controllers;

import com.kochmarev.testapiupdate.rest.exceptions.NoContentToUpdateException;
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

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;


@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public class PaymentOrderControllerTest {

    @Autowired
    private PaymentOrderController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void checkController() {
        assertNotNull(controller);
    }

    @Test
    @SneakyThrows
    public void updatePaymentOrder_positive() {
        mockMvc.perform(
                put("/payment_orders/{id}", 1)
                        .content(Files.lines(Paths.get("src\\main\\resources\\test_payment_order.xml"))
                                .collect(Collectors.joining("\n"))
                                .replace("\n", "")
                                .replace("\t", "")
                                .replace(" ", "")
                        )
                        .contentType(MediaType.APPLICATION_XML)
        )//.andExpect(status().isAccepted())
                .andExpect(xpath("/payment_orders/transKind").string("kt"))
                .andExpect(xpath("/payment_orders/paymentOrderNumber").string("1234567890"))
                .andExpect(xpath("/payment_orders/date").string("2020-12-03T09:28:15.637+00:00"))
                .andExpect(xpath("/payment_orders/paymentSum").string("1111.11"))
                .andExpect(xpath("/payment_orders/commissionSum").string("22.22"))
                .andExpect(xpath("/payment_orders/paymentPurpose").string("test_purpose"))
                .andExpect(xpath("/payment_orders/paymentPriority").string("5"))
                .andExpect(xpath("/payment_orders/paymentPriorityAuto").string("n"))
                .andExpect(xpath("/payment_orders/paymentSendType").string("s"))
                .andExpect(xpath("/payment_orders/paymentPurposeCode").string("1"));
    }

    @SneakyThrows
    @Test(expected = NoContentToUpdateException.class)
    public void updatePaymentOrder_negative() {
        mockMvc.perform(
                put("/payment_orders/{id}", 3)
                        .content(Files.lines(Paths.get("src\\main\\resources\\test_payment_order.xml"))
                                .collect(Collectors.joining("\n")))
                        .contentType(MediaType.APPLICATION_XML));
    }
}