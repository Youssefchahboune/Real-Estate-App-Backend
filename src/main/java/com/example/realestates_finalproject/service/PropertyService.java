package com.example.realestates_finalproject.service;

import com.example.realestates_finalproject.entity.Property;
import com.example.realestates_finalproject.entity.Seller;
import com.example.realestates_finalproject.exception.KeyDoesNotMatch;
import com.example.realestates_finalproject.exception.ResourceNotFound;
import com.example.realestates_finalproject.repository.PropertyRepository;
import com.example.realestates_finalproject.repository.SellerRepository;
import com.example.realestates_finalproject.request.DeletePropertyRequest;
import com.example.realestates_finalproject.request.PropertyRequest;
import com.example.realestates_finalproject.request.SellerRequest;
import com.example.realestates_finalproject.request.UpdatePropertyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    SellerRepository sellerRepository;

    public List<Property> getAllProperties() {
        return (List<Property>) propertyRepository.findAll();
    }

    public Property insertProperty(PropertyRequest propertyRequest) {

        Optional<Seller> seller = sellerRepository.findSellerBysellerKey(propertyRequest.getSeller().getSellerKey());

        if(seller.isEmpty()){
            sellerRepository.save(new Seller(propertyRequest.getSeller()));
            Optional<Seller> s = sellerRepository.findSellerBysellerKey(propertyRequest.getSeller().getSellerKey());
            Property propertyToBeSaved = new Property(propertyRequest);
            propertyToBeSaved.setSeller(s.get());

            return propertyRepository.save(propertyToBeSaved);
        } else {
            Property propertyToBeSaved = new Property(propertyRequest);
            propertyToBeSaved.setSeller(seller.get());

            return propertyRepository.save(propertyToBeSaved);
        }


    }

    public Property updateProperty(UpdatePropertyRequest updatePropertyRequest){

        //check if id exist else throw not found exception
        propertyRepository.findById(updatePropertyRequest.getId())
                .orElseThrow(() -> new RuntimeException("property id not found"));

        // if id found compare both keys to see if they match
        Optional<Property> property = propertyRepository.findById(updatePropertyRequest.getId());
        String keyRequest = updatePropertyRequest.getKey();
        String keyFromPropertyObject = property.get().getSeller().getSellerKey();

        if (keyRequest.equals(keyFromPropertyObject)){
            // key match so pass the request with the id,key,status and pass the retrieved property from database
            Property propertyToBeUpdated = new Property(updatePropertyRequest, property);
            propertyToBeUpdated.setSeller(property.get().getSeller());
            propertyToBeUpdated.setId(updatePropertyRequest.getId());

            return propertyRepository.save(propertyToBeUpdated);
        }
        // else throw an exception
        else {
            throw new KeyDoesNotMatch("Key does not match the id !");
        }
    }

    public void deleteProperty(DeletePropertyRequest deletePropertyRequest){

        //check if id exist else throw not found exception
        propertyRepository.findById(deletePropertyRequest.getId())
                .orElseThrow(() -> new RuntimeException("property id not found"));

        // if id found compare both keys to see if they match
        Optional<Property> property = propertyRepository.findById(deletePropertyRequest.getId());
        String keyRequest = deletePropertyRequest.getKey();
        String keyFromPropertyObject = property.get().getSeller().getSellerKey();

        //if key matches delete property based on requested id
        if (keyRequest.equals(keyFromPropertyObject)){
            propertyRepository.deleteById(deletePropertyRequest.getId());
        }
        // else throw an exception
        else {
            throw new KeyDoesNotMatch("Key does not match the id !");
        }

    }
}
