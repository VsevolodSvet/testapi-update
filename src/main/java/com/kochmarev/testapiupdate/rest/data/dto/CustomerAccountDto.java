package com.kochmarev.testapiupdate.rest.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerAccountDto implements Serializable {

    private static final long serialVersionUID = 3602167034517186787L;

    private Long customerId;
    private String account;
}
