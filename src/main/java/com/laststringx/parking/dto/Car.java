package com.laststringx.parking.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Car extends Vehicle{

    // other info can be added, SOLID -> O -> open for extension, closed for modification

    public Car(String licensePlateNumber, String color) {
        super(licensePlateNumber, color);
    }

}
