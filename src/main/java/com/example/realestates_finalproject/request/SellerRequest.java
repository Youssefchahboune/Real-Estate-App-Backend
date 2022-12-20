package com.example.realestates_finalproject.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SellerRequest {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String email;
    @NotEmpty
    private String sellerKey;
}
