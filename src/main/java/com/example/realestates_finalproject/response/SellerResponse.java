package com.example.realestates_finalproject.response;

import com.example.realestates_finalproject.entity.Seller;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SellerResponse {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public SellerResponse(Seller seller){
        firstName = seller.getFirstName();
        lastName = seller.getLastName();
        phone = seller.getPhone();
        email = seller.getEmail();
    }
}
