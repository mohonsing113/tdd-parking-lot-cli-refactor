package com.oocl.cultivation;

import java.util.*;

public class ParkingLotCenter {
    private List<ParkingLot> parkingLots;

    public ParkingLotCenter(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
