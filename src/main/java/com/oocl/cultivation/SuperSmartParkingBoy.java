package com.oocl.cultivation;

import java.util.*;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public Optional<ParkingLot> selectParkingLot() {
        return parkingLots.stream()
                .filter(ParkingLot::hasSpace)
                .max(Comparator.comparing(ParkingLot::getAvailablePositionRate));
    }
}
