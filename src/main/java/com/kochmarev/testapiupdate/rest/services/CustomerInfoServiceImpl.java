package com.kochmarev.testapiupdate.rest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kochmarev.testapiupdate.rest.data.dto.CustomerAccountDto;
import com.kochmarev.testapiupdate.rest.data.dto.CustomerDataDto;
import com.kochmarev.testapiupdate.rest.data.dto.CustomerInfoDto;

@Service
@RequiredArgsConstructor
public class CustomerInfoServiceImpl implements CustomerInfoService {

    private final CustomerDataService customerDataService;

    private final CustomerAccountService customerAccountService;

    @Override
    public CustomerInfoDto getCustomerInfo(Long customerId) {
        // API Call №1
        CustomerDataDto customerData = customerDataService.getCustomerData(customerId);

        // API Call №2
        CustomerAccountDto customerInfo = customerAccountService.getCustomerAccount(customerId);

        return CustomerInfoDto.builder().customerData(customerData).customerAccount(customerInfo).build();
    }
}
