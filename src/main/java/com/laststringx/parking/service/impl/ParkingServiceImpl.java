package com.laststringx.parking.service.impl;

import com.laststringx.parking.service.ParkingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ParkingServiceImpl implements ParkingService {


    @Override
    public String createParkingLot(int size) {
        log.info("creating parking lot of size {}" , size);
        return "Parking lot created ";
    }
}
