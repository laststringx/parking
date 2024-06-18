package com.laststringx.parking.response;

import com.laststringx.parking.dto.Vehicle;
import lombok.Data;

import java.util.List;

@Data
public class SearchVehicleResponse extends BaseResponse{
    private List<Vehicle> data;
    public SearchVehicleResponse(List<Vehicle> vehicles, String code){
        super(code);
        this.data = vehicles;
    }
}
