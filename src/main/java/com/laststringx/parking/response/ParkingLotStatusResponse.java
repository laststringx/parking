package com.laststringx.parking.response;

import com.laststringx.parking.dao.ParkingLot;
import lombok.Data;

@Data
public class ParkingLotStatusResponse extends BaseResponse{
    private ParkingLot parkingLot;

    public ParkingLotStatusResponse(ParkingLot parkingLot, String code){
        super(code);
        this.parkingLot=parkingLot;
    }
}
