package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager extends ParkingBoy{
    private List<ParkingBoy> parkingBoys;

    public ServiceManager(ParkingLotCenter parkingLotCenter) {
        super(parkingLotCenter);
        parkingBoys = new ArrayList<>();
    }

    public void addParkingBoy(ParkingBoy parkingBoy){
        parkingBoys.add(parkingBoy);
    }

    public ParkingTicket orderToPark(ParkingBoy parkingBoy, Car car){
        if(!parkingBoys.contains(parkingBoy)){
            System.out.println("Not my boys");
            return null;
        }
        ParkingTicket parkingTicket = parkingBoy.park(car);
        System.out.println(parkingBoy.lastErrorMessage);
        return parkingTicket;
    }
    public Car orderToFetch(ParkingBoy parkingBoy, ParkingTicket parkingTicket){
        Car car = parkingBoy.fetch(parkingTicket);
        System.out.println(parkingBoy.lastErrorMessage);
        return car;
    }
}
