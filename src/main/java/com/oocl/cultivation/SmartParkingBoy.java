package com.oocl.cultivation;

import java.util.*;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Optional<ParkingLot> selectParkingLot() {
        return parkingLots.stream()
                .filter(ParkingLot::hasSpace)
                .max(Comparator.comparing(ParkingLot::getAvailableParkingPosition));
    }
}
