package com.kochmarev.testapiupdate.rest.services;

import com.kochmarev.testapiupdate.rest.data.dto.CustomerDataDto;

public interface CustomerDataService {
    CustomerDataDto getCustomerData(Long customerId);
}
