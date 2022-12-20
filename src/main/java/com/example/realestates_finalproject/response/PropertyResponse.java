package com.example.realestates_finalproject.response;


import com.example.realestates_finalproject.entity.Property;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyResponse {
    private long id;

    private String imageURL;
    private double price;
    private String address;
    private String province;
    private int beds;
    private int baths;
    private String type;
    private String status;

    private SellerResponse seller;

    public PropertyResponse(Property property){
        id = property.getId();
        price = property.getPrice();
        address = property.getAddress();
        province = property.getProvince();
        beds = property.getBeds();
        baths = property.getBaths();
        type = property.getType();
        status = property.getStatus();
        imageURL = property.getImageUrl();
        seller = new SellerResponse(property.getSeller());
    }
}
