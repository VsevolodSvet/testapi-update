package com.kochmarev.testapiupdate.rest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.kochmarev.testapiupdate.rest.data.dto.CustomerDataDto;

@Service
@RequiredArgsConstructor
public class CustomerDataServiceImpl implements CustomerDataService {

    @Value("${customerService.url}")
    private String customerServiceUrl;

    private final RestTemplate restTemplate;

    @Override
    public CustomerDataDto getCustomerData(Long customerId) {
        return restTemplate.getForObject(String.format("%s/customers/%s", customerServiceUrl, customerId), CustomerDataDto.class);
    }
}
