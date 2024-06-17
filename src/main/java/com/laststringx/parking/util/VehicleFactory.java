package com.laststringx.parking.util;

import com.laststringx.parking.dto.Car;
import com.laststringx.parking.dto.Vehicle;

public class VehicleFactory {
    public static Vehicle createVehicle(String type, String licensePlateNumber, String color) {
        if ("car".equalsIgnoreCase(type)) {
            return new Car(licensePlateNumber, color);
        }else {
            throw new IllegalArgumentException("Invalid vehicle type: " + type);
        }
    }
}