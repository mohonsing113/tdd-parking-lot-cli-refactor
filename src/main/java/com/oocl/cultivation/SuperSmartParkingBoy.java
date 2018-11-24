package com.oocl.cultivation;

import java.util.Comparator;
import java.util.Optional;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ParkingLotCenter parkingLotCenter) {
        super(parkingLotCenter);
    }

    @Override
    public Optional<ParkingLot> selectParkingLot() {
        return parkingLotCenter.getParkingLots().stream()
                .max(Comparator.comparing(ParkingLot::getAvailablePositionRate));
    }
}
