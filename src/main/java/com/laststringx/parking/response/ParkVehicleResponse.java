package com.laststringx.parking.response;

import com.laststringx.parking.dto.Vehicle;
import lombok.Data;

@Data
public class ParkVehicleResponse extends BaseResponse{
    private Vehicle vehicle;
    private String message;

    public ParkVehicleResponse(Vehicle vehicle, String message, String code){
        super(code);
        this.message = message;
        this.vehicle = vehicle;
    }


}
