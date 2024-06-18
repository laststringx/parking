package com.laststringx.parking.dto;

import lombok.Data;

@Data
public class CarSpot extends ParkingSpot{
    public CarSpot(long id, long level, ParkingStatus parkingStatus, ParkingType parkingType, Vehicle vehicle) {
        super(id, level, parkingStatus, parkingType, vehicle);
    }
}
