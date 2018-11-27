package com.oocl.cultivation;

public class ParkingTicket {
    ParkingLot parkingLot;
    boolean used;

    public ParkingTicket(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        used = false;
    }

    ParkingLot getParkingLot() {
        return parkingLot;
    }

    boolean isUsed() {
        return used;
    }

    void setUsed(boolean used) {
        this.used = used;
    }
}
