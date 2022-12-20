package com.example.realestates_finalproject.entity;

import com.example.realestates_finalproject.request.PropertyRequest;
import com.example.realestates_finalproject.request.SellerRequest;
import com.example.realestates_finalproject.request.UpdatePropertyRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="Properties")
public class Property {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="p_image")
    private String imageUrl;
    @Column(name="p_price")
    private double price;
    @Column(name="p_address")
    private String address;
    @Column(name="p_province")
    private String province;
    @Column(name="p_beds")
    private int beds;
    @Column(name="p_baths")
    private int baths;
    @Column(name="p_type")
    private String type;
    @Column(name="p_status")
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(name="seller_ID")
    private Seller seller;


    // Insert Constructor
    public Property(PropertyRequest propertyRequest){
        price = propertyRequest.getPrice();
        imageUrl = propertyRequest.getImageUrl();
        address = propertyRequest.getAddress();
        province = propertyRequest.getProvince();
        beds = propertyRequest.getBeds();
        baths = propertyRequest.getBaths();
        type = propertyRequest.getType();
        status = "available";
    }

    // Update Constructor
    public Property(UpdatePropertyRequest updatePropertyRequest, Optional<Property> oldProperty){
        price = oldProperty.get().getPrice();
        imageUrl = oldProperty.get().getImageUrl();
        address = oldProperty.get().getAddress();
        province = oldProperty.get().getProvince();
        beds = oldProperty.get().getBeds();
        baths = oldProperty.get().getBaths();
        type = oldProperty.get().getType();
        status = updatePropertyRequest.getStatus();
    }
}
