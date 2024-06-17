package com.laststringx.parking.controller;

import com.laststringx.parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Name;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParkingController {

    @Autowired
    private ParkingService parkingService;
    @GetMapping
    public String getMap(){
        return "Hello";
    }

    @PostMapping("/create/{size}")
    public String createParkingLot(@PathVariable @Name("size") int size){
        return parkingService.createParkingLot(size);
    }

}
