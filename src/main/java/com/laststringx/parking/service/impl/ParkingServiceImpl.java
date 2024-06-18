package com.laststringx.parking.service.impl;

import com.laststringx.parking.dao.ParkingDao;
import com.laststringx.parking.dto.Car;
import com.laststringx.parking.dto.ParkingSpot;
import com.laststringx.parking.dto.ParkingStatus;
import com.laststringx.parking.dto.Vehicle;
import com.laststringx.parking.exceptions.DaoException;
import com.laststringx.parking.response.ParkVehicleResponse;
import com.laststringx.parking.response.ParkingLotResponse;
import com.laststringx.parking.service.ParkingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ParkVehicleResponse parkVehicle(Vehicle vehicle) {
        // check if vehicle is already parked
        return null;
    }

    @Override
    public ParkVehicleResponse parkCar(Car car) {
        ParkingSpot parkedCarSpot = parkingDao.getParkingSpotByLicensePlateNumber(car.getLicensePlateNumber());
        if(parkedCarSpot != null){
            log.info("car with reg number {} already parked.", car.getLicensePlateNumber());
            return new ParkVehicleResponse(car, "car is already parked at level : "+ parkedCarSpot.getLevel()+ " spot " + parkedCarSpot.getId(),"success");
        }
        ParkingSpot parkingSpot = parkingDao.getFreeParkingSpot();
        if(parkingSpot == null){
            return new ParkVehicleResponse(null, "no spaces to park", "failure");
        }
        parkingSpot.setParkingStatus(ParkingStatus.OCCUPIED);
        parkingSpot.setVehicle(car);
        parkingDao.saveParking(parkingSpot);
        return new ParkVehicleResponse(car, "parked at level : " + parkingSpot.getLevel() + " spot : " + parkingSpot.getId(), "success");
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


}
