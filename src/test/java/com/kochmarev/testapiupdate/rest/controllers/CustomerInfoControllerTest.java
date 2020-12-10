package com.kochmarev.testapiupdate.rest.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import com.kochmarev.testapiupdate.rest.data.dto.CustomerAccountDto;
import com.kochmarev.testapiupdate.rest.data.dto.CustomerDataDto;
import com.kochmarev.testapiupdate.rest.data.dto.CustomerInfoDto;
import com.kochmarev.testapiupdate.rest.exceptions.ErrorDetails;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerInfoControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MockRestServiceServer mockServer;

    @Autowired
    private ObjectMapper mapper;

    private CustomerDataDto customerDataForMockServer;

    private CustomerAccountDto customerAccountForMockServer;

    private void addMockEndpoint(String uri, Object mockResponse) throws URISyntaxException, JsonProcessingException {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(uri)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(mapper.writeValueAsString(mockResponse))
                );
    }

    private void addMockEndpointWithHttpStatus(String uri, HttpStatus httpStatus) throws URISyntaxException {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(uri)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(httpStatus));
    }

    @Before
    public void init() {

        customerDataForMockServer = CustomerDataDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .isCorporateClient(true)
                .serviceLevel(2)
                .build();

        customerAccountForMockServer = CustomerAccountDto.builder()
                .customerId(1L)
                .account("11122333455556666666")
                .build();

    }

    @Test
    public void testCustomerInfoController_200_OK() throws URISyntaxException, JsonProcessingException {
        // arrange
        addMockEndpoint("https://localhost:8081/customers/1", customerDataForMockServer);
        addMockEndpoint("https://localhost:8082/customers/1/account", customerAccountForMockServer);

        // act
        CustomerInfoDto actualCustomerInfo = testRestTemplate.getForObject("http://localhost:8080/customers-info/1", CustomerInfoDto.class);

        // assert
        assertEquals(Long.valueOf(1L), actualCustomerInfo.getCustomerData().getId());
        assertEquals("John", actualCustomerInfo.getCustomerData().getFirstName());
        assertEquals("Doe", actualCustomerInfo.getCustomerData().getLastName());
        assertTrue(actualCustomerInfo.getCustomerData().getIsCorporateClient());
        assertEquals(Integer.valueOf(2), actualCustomerInfo.getCustomerData().getServiceLevel());

        assertEquals("11122333455556666666", actualCustomerInfo.getCustomerAccount().getAccount());
        assertEquals(Long.valueOf(1L), actualCustomerInfo.getCustomerAccount().getCustomerId());
    }

    @Test
    public void testCustomerInfoController_CustomerDataServiceFault() throws URISyntaxException {
        // arrange
        addMockEndpointWithHttpStatus("https://localhost:8081/customers/1", HttpStatus.INTERNAL_SERVER_ERROR);

        // act
        ErrorDetails errorDetails = testRestTemplate.getForObject("http://localhost:8080/customers-info/1", ErrorDetails.class);

        // assert
        assertEquals("500 Internal Server Error: [no body]", errorDetails.getMessage());
        assertEquals("uri=/customers-info/1", errorDetails.getDetails());
    }
}
