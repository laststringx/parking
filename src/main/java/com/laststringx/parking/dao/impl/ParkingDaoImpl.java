package com.laststringx.parking.dao.impl;


import com.laststringx.parking.dao.ParkingDao;
import com.laststringx.parking.dao.ParkingLevel;
import com.laststringx.parking.dao.ParkingLot;
import com.laststringx.parking.dto.*;
import com.laststringx.parking.exceptions.DaoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ParkingDaoImpl implements ParkingDao {

    @Autowired
    private ParkingLot parkingLot;


    @Override
    public void saveParking(ParkingSpot parkingSpot) {
        // as the logic is in service layer
        return;

    }

    @Override
    public void removeParking(ParkingSpot parkingSpot) {

    }

    @Override
    public List<Car> getCarByColor(String color) {
        return null;
    }

    @Override
    public ParkingSpot getParkingSpotByLicensePlateNumber(String registrationNumber) {
        for(ParkingLevel parkingLevel : parkingLot.getParkingLevels()){
            for(ParkingSpot parkingSpot : parkingLevel.getParkingSpots()){
                if(ParkingStatus.OCCUPIED.equals(parkingSpot.getParkingStatus())){
                    Vehicle vehicle = parkingSpot.getVehicle();
                    if(vehicle.getLicensePlateNumber().equals(registrationNumber)){
                        return parkingSpot;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public ParkingLot getParkingLotInfo() {
        return parkingLot;
    }

    // creating basic parking lot of size = size, only one level initially
    @Override
    public String createParkingLot(int size) throws DaoException {
        log.info("initializing basic parking lot with one level and size {}", size);
        List<ParkingLevel> parkingLevels = parkingLot.getParkingLevels();
        if(parkingLevels != null){
            throw new DaoException("Parking lot is already created");
        }
        parkingLevels = new ArrayList<>();
        parkingLot.setParkingLevels(parkingLevels);
        ParkingLevel parkingLevel = new ParkingLevel(0, size);
        parkingLevels.add(parkingLevel);
        log.info("parking levels created with level {} and size {}", parkingLevel.getLevel(), parkingLevel.getParkingSpace());
        return String.format("parking levels created with level %s and size %s", parkingLevel.getLevel(), parkingLevel.getParkingSpace());
    }

    @Override
    public ParkingSpot getFreeParkingSpot() {
        for (ParkingLevel parkingLevel : parkingLot.getParkingLevels()){
            for(ParkingSpot parkingSpot : parkingLevel.getParkingSpots()){
                if(parkingSpot.getParkingStatus() == ParkingStatus.FREE){
                    return parkingSpot;
                }
            }
        }
        log.info("no parking spaces available");
        return null;
    }

    @Override
    public ParkingSpot getParkingSpotByLevelAndSpot(int level, int spot) throws DaoException {
        try {
            return parkingLot.getParkingLevels().get(level).getParkingSpots().get(spot);
        }
        catch (Exception e){
            throw new DaoException("Parking spot not found");
        }
    }
}
