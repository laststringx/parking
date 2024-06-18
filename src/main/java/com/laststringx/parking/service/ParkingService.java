package com.laststringx.parking.service;

import com.laststringx.parking.dto.Car;
import com.laststringx.parking.dto.Vehicle;
import com.laststringx.parking.response.ParkVehicleResponse;
import com.laststringx.parking.response.ParkingLotResponse;

public interface ParkingService {
    ParkingLotResponse createParkingLot(int size);

    ParkVehicleResponse parkVehicle(Vehicle vehicle);

    ParkVehicleResponse parkCar(Car car);

    ParkVehicleResponse unparkVehicle(String registrationNumber);

    ParkVehicleResponse unparkVehicle(int level, int spot);
}
