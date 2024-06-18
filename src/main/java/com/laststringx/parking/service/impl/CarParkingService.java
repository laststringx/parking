package com.laststringx.parking.service.impl;

import com.laststringx.parking.dao.ParkingDao;
import com.laststringx.parking.dto.*;
import com.laststringx.parking.response.ParkVehicleResponse;
import com.laststringx.parking.service.VehicleParkingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("carParkingService")
public class CarParkingService implements VehicleParkingService {

    @Autowired
    private ParkingDao parkingDao;
    @Override
    public ParkVehicleResponse parkVehicle(Vehicle vehicle) {
        Car car = (Car) vehicle;
        ParkingSpot parkedCarSpot = parkingDao.getParkingSpotByLicensePlateNumber(car.getLicensePlateNumber());
        if(parkedCarSpot != null){
            log.info("car with reg number {} already parked.", car.getLicensePlateNumber());
            return new ParkVehicleResponse(car, "car is already parked at level : "+ parkedCarSpot.getLevel()+ " spot " + parkedCarSpot.getId(),"success");
        }
        ParkingSpot parkingSpot = parkingDao.getFreeParkingSpot(ParkingType.CAR);
        if(parkingSpot == null){
            return new ParkVehicleResponse(null, "no spaces to park", "failure");
        }
        parkingSpot.setParkingStatus(ParkingStatus.OCCUPIED);
        parkingSpot.setVehicle(car);
        parkingDao.saveParking(parkingSpot);
        return new ParkVehicleResponse(car, "parked at level : " + parkingSpot.getLevel() + " spot : " + parkingSpot.getId(), "success");
    }
}
