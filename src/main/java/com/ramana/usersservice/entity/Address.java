package com.ramana.usersservice.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Address {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String addressType;
}
