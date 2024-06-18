package com.laststringx.parking.request;

import lombok.Data;

@Data
public class ParkVehicleRequest {
    private String vehicleRegistrationNumber;
    private String color;
    private String vehicleType;
}
