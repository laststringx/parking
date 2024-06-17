package com.laststringx.parking.dao;

import com.laststringx.parking.dto.Car;
import com.laststringx.parking.dto.ParkingSpot;

import java.util.List;

public interface ParkingDao {

    void saveParking(ParkingSpot parkingSpot);

    void removeParking(ParkingSpot parkingSpot);

    List<Car> getCarByColor(String color);
    List<Car> getCarByLicensePlateNumber(String registrationNumber);

    ParkingLot getParkingLotInfo();

}
