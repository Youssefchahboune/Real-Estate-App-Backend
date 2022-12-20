package com.example.realestates_finalproject.repository;

import com.example.realestates_finalproject.entity.Property;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends CrudRepository<Property,Long> {
}
