package com.example.realestates_finalproject.request;

import com.example.realestates_finalproject.entity.Seller;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyRequest {
    private double price;
    @NotEmpty
    private String imageUrl;
    @NotEmpty
    private String address;
    @NotEmpty
    private String province;
    private int beds;
    private int baths;
    @NotEmpty
    private String type;
    private String status;
    private SellerRequest seller;
}
