package com.oocl.cultivation;

public class ParkingTicket {
    ParkingLot parkingLot;
    boolean used;

    public ParkingTicket(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        used = false;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
