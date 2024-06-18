package com.laststringx.parking.dao;

import com.laststringx.parking.dto.Car;
import com.laststringx.parking.dto.ParkingSpot;
import com.laststringx.parking.exceptions.DaoException;

import java.util.List;

public interface ParkingDao {

    void saveParking(ParkingSpot parkingSpot);

    void removeParking(ParkingSpot parkingSpot);

    List<Car> getCarByColor(String color);
    ParkingSpot getParkingSpotByLicensePlateNumber(String registrationNumber);

    ParkingLot getParkingLotInfo();

    String createParkingLot(int size) throws DaoException;

    ParkingSpot getFreeParkingSpot();

    ParkingSpot getParkingSpotByLevelAndSpot(int level, int spot) throws DaoException;
}
