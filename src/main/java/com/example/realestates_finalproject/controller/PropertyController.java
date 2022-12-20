package com.example.realestates_finalproject.controller;

import com.example.realestates_finalproject.entity.Property;
import com.example.realestates_finalproject.request.DeletePropertyRequest;
import com.example.realestates_finalproject.request.PropertyRequest;
import com.example.realestates_finalproject.request.UpdatePropertyRequest;
import com.example.realestates_finalproject.response.PropertyResponse;
import com.example.realestates_finalproject.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    @GetMapping()
    public List<PropertyResponse> getAllProperties(){
        List<Property> properties = propertyService.getAllProperties();

        List<PropertyResponse> propertyResponse = new ArrayList<>();

        properties.forEach(property -> {
            propertyResponse.add(new PropertyResponse(property));
        });

        return propertyResponse;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public PropertyResponse addProperty(@Valid @RequestBody PropertyRequest propertyRequest){
        Property property = propertyService.insertProperty(propertyRequest);
        return new PropertyResponse(property);
    }



    @PatchMapping ()
    public PropertyResponse updatePropertyAvailability(@Valid @RequestBody UpdatePropertyRequest updatePropertyRequest){
        Property property = propertyService.updateProperty(updatePropertyRequest);
        return new PropertyResponse(property);
    }

    @DeleteMapping()
    public void deleteProperty(@Valid @RequestBody DeletePropertyRequest deletePropertyRequest){
        propertyService.deleteProperty(deletePropertyRequest);
    }
}
