package com.oocl.cultivation;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int capacity;
    private Map<ParkingTicket, Car> cars = new HashMap<>();

    public ParkingLot() {
        this(10);
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public void park(ParkingTicket parkingTicket, Car car){
        cars.put(parkingTicket, car);
    }
    public Car fetch(ParkingTicket parkingTicket){
        return cars.remove(parkingTicket);
    }

    public int getAvailableParkingPosition() {
        return capacity - cars.size();
    }

    public int getAvailablePositionRate(){
        return getAvailableParkingPosition()/capacity;
    }

    public boolean hasSpace() {
        return cars.size() < capacity;
    }
}
