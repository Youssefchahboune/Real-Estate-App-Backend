package com.example.realestates_finalproject.repository;

import com.example.realestates_finalproject.entity.Seller;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SellerRepository extends CrudRepository<Seller,Long> {
    Optional<Seller> findSellerBysellerKey(String key);
}
