package com.kochmarev.testapiupdate.rest.services;

import com.kochmarev.testapiupdate.rest.data.dto.CustomerAccountDto;

public interface CustomerAccountService {
    CustomerAccountDto getCustomerAccount(Long customerId);
}
