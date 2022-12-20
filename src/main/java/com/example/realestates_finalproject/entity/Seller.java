package com.example.realestates_finalproject.entity;

import com.example.realestates_finalproject.request.SellerRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="Sellers")
public class Seller {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="phone")
    private String phone;
    @Column(name="email")
    private String email;
    @Column(name="Seller_Key")
    private String sellerKey;

    public Seller(SellerRequest sellerRequest){
        firstName = sellerRequest.getFirstName();
        lastName = sellerRequest.getLastName();
        phone = sellerRequest.getPhone();
        email = sellerRequest.getEmail();
        sellerKey = sellerRequest.getSellerKey();
    }

}
