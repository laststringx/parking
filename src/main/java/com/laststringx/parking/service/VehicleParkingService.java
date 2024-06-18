package com.laststringx.parking.service;

import com.laststringx.parking.dto.Vehicle;
import com.laststringx.parking.response.ParkVehicleResponse;

public interface VehicleParkingService {

    ParkVehicleResponse parkVehicle(Vehicle vehicle);
}
