package com.kochmarev.testapiupdate.rest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.kochmarev.testapiupdate.rest.data.dto.CustomerAccountDto;

@Service
@RequiredArgsConstructor
public class CustomerAccountServiceImpl implements CustomerAccountService {

    @Value("${customerAccountService.url}")
    private String customerAccountServiceUrl;

    private final RestTemplate restTemplate;

    @Override
    public CustomerAccountDto getCustomerAccount(Long customerId) {
        return restTemplate.getForObject(String.format("%s/customers/%s/account", customerAccountServiceUrl, customerId), CustomerAccountDto.class);
    }
}
