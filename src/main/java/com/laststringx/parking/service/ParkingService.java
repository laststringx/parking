package com.laststringx.parking.service;

import com.laststringx.parking.response.ParkVehicleResponse;
import com.laststringx.parking.response.ParkingLotResponse;
import com.laststringx.parking.response.ParkingLotStatusResponse;
import com.laststringx.parking.response.SearchVehicleResponse;

public interface ParkingService {
    ParkingLotResponse createParkingLot(int size);

    ParkVehicleResponse unparkVehicle(String registrationNumber);

    ParkVehicleResponse unparkVehicle(int level, int spot);

    ParkingLotStatusResponse getParkingStatus();

    SearchVehicleResponse searchByColor(String color);
}
