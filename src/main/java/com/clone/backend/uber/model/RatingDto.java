package com.clone.backend.uber.model;

import lombok.Data;

@Data
public class RatingDto {
    private Long rideId;
    private Integer rating;
}
