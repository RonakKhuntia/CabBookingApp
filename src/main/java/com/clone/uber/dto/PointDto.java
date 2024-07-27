package com.clone.uber.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PointDto {

    private double[] coordinates;
    private String type = "Point";

    //PointDto
    public PointDto(double[] coordinates) {
        this.coordinates = coordinates;
    }
}
