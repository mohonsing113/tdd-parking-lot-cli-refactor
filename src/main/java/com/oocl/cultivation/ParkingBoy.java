package com.oocl.cultivation;

import java.util.Optional;

public class ParkingBoy {

    private String lastErrorMessage;
    private ParkingLotCenter parkingLotCenter;

    public ParkingBoy(ParkingLotCenter parkingLotCenter) {
        this.parkingLotCenter = parkingLotCenter;
    }

    public ParkingTicket park(Car car) {
        Optional<ParkingLot> targetParkingLot = selectParkingLot(parkingLotCenter);
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

    public Optional<ParkingLot> selectParkingLot(ParkingLotCenter parkingLotCenter) {
        return parkingLotCenter.getParkingLots().stream()
                .filter(parkingLot -> parkingLot.hasSpace())
                .findFirst();
    }

    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
}
