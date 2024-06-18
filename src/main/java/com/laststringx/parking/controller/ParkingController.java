package com.laststringx.parking.controller;

import com.laststringx.parking.dto.Car;
import com.laststringx.parking.dto.Vehicle;
import com.laststringx.parking.exceptions.ControllerException;
import com.laststringx.parking.request.ParkVehicleRequest;
import com.laststringx.parking.response.ParkVehicleResponse;
import com.laststringx.parking.response.ParkingLotResponse;
import com.laststringx.parking.response.ParkingLotStatusResponse;
import com.laststringx.parking.response.SearchVehicleResponse;
import com.laststringx.parking.service.ParkingService;
import com.laststringx.parking.service.VehicleParkingService;
import com.laststringx.parking.util.VehicleFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @Autowired
    private VehicleParkingService carParkingService;
    @GetMapping
    public String getMap(){
        return "Hello";
    }

    @PostMapping("/create/{size}")
    public ParkingLotResponse createParkingLot(@PathVariable @Name("size") int size){
        return parkingService.createParkingLot(size);
    }

    @PutMapping("/park")
    public ParkVehicleResponse parkVehicle(@RequestBody ParkVehicleRequest request) throws ControllerException {
        // idempotent api to park a vehicle
        validateRequest(request);
        Vehicle vehicle = VehicleFactory.createVehicle(request.getVehicleType(), request.getVehicleRegistrationNumber(), request.getColor());
        if(vehicle instanceof Car){
            Car car = (Car) vehicle;
            return carParkingService.parkVehicle(car);
        }
        else return null;

    }

    @PutMapping("leave/{regNo}")
    public ParkVehicleResponse unparkVehicle(@PathVariable @Name("regNo") String registrationNumber){
        return parkingService.unparkVehicle(registrationNumber);
    }

    @PutMapping("leave/level/{level}/spot/{spot}")
    public ParkVehicleResponse unparkVehicleBySpot(@PathVariable @Name("level") int level, @PathVariable @Name("spot") int spot){
        return parkingService.unparkVehicle(level, spot);
    }

    @GetMapping("status")
    public ParkingLotStatusResponse getParkingStatus(){
        return parkingService.getParkingStatus();
    }

    @GetMapping("vehicles/color/{color}")
    public SearchVehicleResponse searchVehicles(@PathVariable @Name("color") String color){
        return parkingService.searchByColor(color); // Due to time constraint or this would have been generic one
    }

    private void validateRequest(ParkVehicleRequest request) throws ControllerException {
        if(request == null){
            throw new ControllerException("request cannot be empty");
        }
        if(StringUtils.isEmpty(request.getVehicleRegistrationNumber()) ||
                StringUtils.isEmpty(request.getVehicleType()) || StringUtils.isEmpty(request.getColor())){
            throw new ControllerException("missing mandatory request params");
        }
    }


}
