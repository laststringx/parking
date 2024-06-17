package com.laststringx.parking.dao.impl;


import com.laststringx.parking.dao.ParkingDao;
import com.laststringx.parking.dao.ParkingLot;
import com.laststringx.parking.dto.Car;
import com.laststringx.parking.dto.ParkingSpot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParkingDaoImpl implements ParkingDao {

    @Autowired
    ParkingLot parkingLot;


    @Override
    public void saveParking(ParkingSpot parkingSpot) {

    }

    @Override
    public void removeParking(ParkingSpot parkingSpot) {

    }

    @Override
    public List<Car> getCarByColor(String color) {
        return null;
    }

    @Override
    public List<Car> getCarByLicensePlateNumber(String registrationNumber) {
        return null;
    }

    @Override
    public ParkingLot getParkingLotInfo() {
        return parkingLot;
    }
}
