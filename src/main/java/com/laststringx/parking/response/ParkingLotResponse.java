package com.laststringx.parking.response;

import lombok.Data;

@Data
public class ParkingLotResponse extends BaseResponse{
    private String data;
    public ParkingLotResponse(String data, String code) {
        super(code);
        this.data = data;
    }
}
