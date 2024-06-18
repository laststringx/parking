package com.laststringx.parking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Car extends Vehicle{

    public Car(String licensePlateNumber, String color) {
        super(licensePlateNumber, color);
    }

}
