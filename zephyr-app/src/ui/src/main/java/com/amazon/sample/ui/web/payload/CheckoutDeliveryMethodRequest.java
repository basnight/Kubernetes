package com.amazon.sample.ui.web.payload;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Data
public class CheckoutDeliveryMethodRequest {
    @Pattern(regexp = "^[A-Za-z0-9\\-]+$")
    @NotEmpty
    private String token;
}
