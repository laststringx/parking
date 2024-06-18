package com.laststringx.parking.dao;

import com.laststringx.parking.dto.ParkingSpot;
import com.laststringx.parking.dto.ParkingType;
import com.laststringx.parking.dto.Vehicle;
import com.laststringx.parking.exceptions.DaoException;

import java.util.List;

public interface ParkingDao {

    void saveParking(ParkingSpot parkingSpot);

    List<Vehicle> getVehicleByColor(String color);
    ParkingSpot getParkingSpotByLicensePlateNumber(String registrationNumber);

    ParkingLot getParkingLotInfo();

    String createParkingLot(int size) throws DaoException;

    ParkingSpot getFreeParkingSpot(ParkingType parkingType);

    ParkingSpot getParkingSpotByLevelAndSpot(int level, int spot) throws DaoException;
}
