package com.laststringx.parking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ParkingSpot {
    private long id;
    private long level;
    private ParkingStatus parkingStatus;
    private ParkingType parkingType;
    private Vehicle vehicle;

}
