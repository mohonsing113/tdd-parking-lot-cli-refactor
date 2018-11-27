package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ServiceManager{
    private List<ParkingBoy> parkingBoys;
    private ParkingBoy parkingBoyIdentity;

    public ServiceManager(List<ParkingLot> parkingLots) {
        parkingBoyIdentity = new ParkingBoy(parkingLots);
        this.parkingBoys = new ArrayList<>();
    }

    public void addParkingBoy(ParkingBoy parkingBoy){
        parkingBoys.add(parkingBoy);
    }

    public ParkingTicket park(Car car){
        return parkingBoyIdentity.park(car);
    }
    public Car fetch(ParkingTicket parkingTicket){
        return parkingBoyIdentity.fetch(parkingTicket);
    }

    public String getLastErrorMessage(){
        return parkingBoyIdentity.getLastErrorMessage();
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
