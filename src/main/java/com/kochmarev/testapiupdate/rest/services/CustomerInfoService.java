package com.kochmarev.testapiupdate.rest.services;

import com.kochmarev.testapiupdate.rest.data.dto.CustomerInfoDto;

public interface CustomerInfoService {
    CustomerInfoDto getCustomerInfo(Long customerId);
}
