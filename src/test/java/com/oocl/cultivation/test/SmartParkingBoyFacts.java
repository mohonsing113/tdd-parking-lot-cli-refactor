package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SmartParkingBoyFacts {


    //given 2 parking lots in parking center that capacity are 12 and 9 and assigned to smart parking boy
    //when smartParkingBoy select parking lot to park car
    //then he will choose capacity with 12
    @Test
    void should_select_first_parking_lot() {

        ParkingLot parkingLot1 = new ParkingLot(12);
        ParkingLot parkingLot2 = new ParkingLot(9);
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot1, parkingLot2));
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotCenter);

        ParkingLot selectedParkingLot = smartParkingBoy.selectParkingLot().get();

        assertSame(selectedParkingLot, parkingLot1);
    }

    //given 2 parking lots in parking center that capacity are 5 and 9 and assigned to smart parking boy
    //when smartParkingBoy select parking lot to park car
    //then he will choose capacity with 9
    @Test
    void should_select_second_parking_lot() {

        ParkingLot parkingLot1 = new ParkingLot(5);
        ParkingLot parkingLot2 = new ParkingLot(9);
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot1, parkingLot2));
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotCenter);

        ParkingLot selectedParkingLot = smartParkingBoy.selectParkingLot().get();

        assertSame(selectedParkingLot, parkingLot2);
    }

    //given 2 parking lots in parking center with both full and assigned to smart parking boy
    //when smartParkingBoy select parking lot to park car
    //then car will not parked
    @Test
    void should_not_park_cars_to_parking_lot_if_there_is_not_enough_position_on_both_parking_lot() {
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(0);
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot1, parkingLot2));
        ParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLotCenter);

        smartParkingBoy.park(new Car());

        assertNull(smartParkingBoy.park(new Car()));
    }

}
