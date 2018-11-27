package com.oocl.cultivation;

import java.util.*;

public class ParkingBoy {

    protected String lastErrorMessage;
    protected List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car) {
        Optional<ParkingLot> targetParkingLot = selectParkingLot();
        if (targetParkingLot.isPresent()) {
            ParkingLot parkingLot = targetParkingLot.get();
            ParkingTicket parkingTicket = new ParkingTicket(parkingLot);
            parkingLot.park(parkingTicket, car);
            lastErrorMessage = null;
            return parkingTicket;
        } else {
            lastErrorMessage = "The parking lot is full.";
            return null;
        }
    }

    public Car fetch(ParkingTicket parkingTicket) {
        Car car = null;

        if(parkingTicket==null){
            lastErrorMessage = "Please provide your parking ticket.";
            return null;
        }
        if (!parkingTicket.isUsed()){
            car = parkingTicket.getParkingLot().fetch(parkingTicket);
            parkingTicket.setUsed(true);
            lastErrorMessage = null;
        }
        if (car == null){
            lastErrorMessage = "Unrecognized parking ticket.";
        }

        return car;
    }

    public Optional<ParkingLot> selectParkingLot() {
        return parkingLots.stream()
                .filter(ParkingLot::hasSpace)
                .findFirst();
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
