package com.example.realestates_finalproject.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePropertyRequest {
    private long id;
    @NotEmpty
    private String key;
    @NotEmpty
    private String status;
}
