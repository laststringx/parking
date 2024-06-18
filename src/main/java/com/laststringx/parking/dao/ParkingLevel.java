package com.laststringx.parking.dao;

import com.laststringx.parking.dto.ParkingSpot;
import com.laststringx.parking.dto.ParkingStatus;
import com.laststringx.parking.dto.ParkingType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ParkingLevel {
    private int level;
    private int parkingSpace;
    private List<ParkingSpot> parkingSpots;
    public ParkingLevel(int level, int totalSpace){
        this.level = level;
        this.parkingSpace = totalSpace;
        parkingSpots = new ArrayList<>();
        initParkingSpaces(ParkingType.NORMAL);
        // more parking types can be added which can be added
    }

    private void initParkingSpaces(ParkingType parkingType) {
        for (int i = 0; i < parkingSpace; i++) {
            ParkingSpot spot = ParkingSpot.builder()
                    .parkingStatus(ParkingStatus.FREE)
                    .id(i)
                    .level(this.level)
                    .parkingType(parkingType).build();
            parkingSpots.add(spot);
        }
    }
}
