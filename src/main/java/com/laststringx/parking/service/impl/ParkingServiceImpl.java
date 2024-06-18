package com.laststringx.parking.service.impl;

import com.laststringx.parking.dao.ParkingDao;
import com.laststringx.parking.dao.ParkingLot;
import com.laststringx.parking.dto.*;
import com.laststringx.parking.exceptions.DaoException;
import com.laststringx.parking.response.ParkVehicleResponse;
import com.laststringx.parking.response.ParkingLotResponse;
import com.laststringx.parking.response.ParkingLotStatusResponse;
import com.laststringx.parking.response.SearchVehicleResponse;
import com.laststringx.parking.service.ParkingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingDao parkingDao;

    @Override
    public ParkingLotResponse createParkingLot(final int size) {
        try {
            String response =  parkingDao.createParkingLot(size);
            return new ParkingLotResponse(response, "success");
        } catch (DaoException e) {
            // HTTP code can be added =
            return new ParkingLotResponse(e.getMessage(), "failure");
        }
    }

    @Override
    public ParkVehicleResponse unparkVehicle(String registrationNumber) {
        ParkingSpot parkingSpot = parkingDao.getParkingSpotByLicensePlateNumber(registrationNumber);
        if(parkingSpot == null){
            return new ParkVehicleResponse(null, "spot is already empty", "success");
        }
        Vehicle vehicle = parkingSpot.getVehicle();
        parkingSpot.setParkingStatus(ParkingStatus.FREE);
        parkingSpot.setVehicle(null);
        return new ParkVehicleResponse(vehicle, "unparked car", "success");
    }

    @Override
    public ParkVehicleResponse unparkVehicle(int level, int spot) {
        try {
            ParkingSpot parkingSpot = parkingDao.getParkingSpotByLevelAndSpot(level, spot);
            if(parkingSpot.getParkingStatus().equals(ParkingStatus.FREE)){
                return new ParkVehicleResponse(null, "parking spot free", "success");
            }
            if(parkingSpot.getParkingStatus().equals(ParkingStatus.OCCUPIED)){
                Vehicle vehicle = parkingSpot.getVehicle();
                parkingSpot.setVehicle(null);
                parkingSpot.setParkingStatus(ParkingStatus.FREE);
                return new ParkVehicleResponse(vehicle, "vehicle unparked", "success");
            }
        } catch (DaoException e) {
            return new ParkVehicleResponse(null, "Spot not found", "failure");
        }
        return null;
    }

    @Override
    public ParkingLotStatusResponse getParkingStatus() {
        ParkingLot parkingLot = parkingDao.getParkingLotInfo();
        return new ParkingLotStatusResponse(parkingLot, "success");
    }

    @Override
    public SearchVehicleResponse searchByColor(String color) {
        List<Vehicle> vehicles = parkingDao.getVehicleByColor(color);
        return new SearchVehicleResponse(vehicles, "success");
    }


}
