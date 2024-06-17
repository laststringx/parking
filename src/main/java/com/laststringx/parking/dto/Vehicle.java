package com.laststringx.parking.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public abstract class Vehicle {
    private String licensePlateNumber;
    private String color;
}
