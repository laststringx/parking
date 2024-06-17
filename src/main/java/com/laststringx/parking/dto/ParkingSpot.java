package com.laststringx.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParkingSpot {
    private long id;
    private ParkingStatus parkingStatus;
    private ParkingType parkingType;
    private Vehicle vehicle;

}
