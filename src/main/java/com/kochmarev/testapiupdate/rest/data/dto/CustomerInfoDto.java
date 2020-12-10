package com.kochmarev.testapiupdate.rest.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomerInfoDto implements Serializable {

    private static final long serialVersionUID = 2395789080131510708L;

    private CustomerDataDto customerData;
    private CustomerAccountDto customerAccount;
}
