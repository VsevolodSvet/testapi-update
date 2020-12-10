package com.kochmarev.testapiupdate.rest.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kochmarev.testapiupdate.rest.data.dto.CustomerInfoDto;
import com.kochmarev.testapiupdate.rest.services.CustomerInfoService;

@RestController
@RequestMapping(value = "/customers-info", produces = {MediaType.APPLICATION_XML_VALUE})
@RequiredArgsConstructor
public class CustomerInfoController {

    private final CustomerInfoService customerInfoService;

    @GetMapping("{id}")
    public CustomerInfoDto getCustomerInfoService(@PathVariable Long id) {
        return customerInfoService.getCustomerInfo(id);
    }
}
