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
public class CustomerDataDto implements Serializable {

    private static final long serialVersionUID = -7460859531720528159L;

    private Long id;
    private String firstName;
    private String lastName;
    private Boolean isCorporateClient;
    private Integer serviceLevel;
}
