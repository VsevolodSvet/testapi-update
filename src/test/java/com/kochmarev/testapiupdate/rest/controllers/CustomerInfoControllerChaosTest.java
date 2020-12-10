package com.kochmarev.testapiupdate.rest.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles({"chaos-monkey", "chaos-delay"})
public class CustomerInfoControllerChaosTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MockRestServiceServer mockServer;

    private void addMockEndpointWithHttpStatus(String uri, HttpStatus httpStatus) throws URISyntaxException {
        mockServer.expect(ExpectedCount.once(), requestTo(new URI(uri)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withStatus(httpStatus));
    }

    @Test(timeout = 4000)
    public void testCustomerInfoController_delay() throws URISyntaxException {

        addMockEndpointWithHttpStatus("https://localhost:8081/customers/1", HttpStatus.OK);
        addMockEndpointWithHttpStatus("https://localhost:8082/customers/1/account", HttpStatus.OK);

        ResponseEntity<String> response = testRestTemplate.getForEntity("http://localhost:8080/customers-info/1", String.class);

        System.out.println(response.getBody());
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

    }
}
