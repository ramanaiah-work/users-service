package com.ramana.usersservice.dto.request;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddressDto {
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String addressType;
}
