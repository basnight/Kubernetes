package com.amazon.sample.ui.web.payload;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Data
public class ShippingAddressRequest {
    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String address1;

    private String address2;

    @NotEmpty
    private String city;

    @Pattern(regexp = "^\\d{5}$")
    private String zip;

    @Pattern(regexp = "^[A-Z]{2}$")
    private String state;
}
