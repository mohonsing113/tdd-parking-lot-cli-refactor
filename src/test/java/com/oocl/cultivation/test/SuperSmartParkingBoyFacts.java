package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class SuperSmartParkingBoyFacts {

    //given 2 parking lots in parking center that capacity are 1/5 (80%)  and 0/2(100%) and assigned to SuperSmartParkingBoy
    //when SuperSmartParkingBoy select parking lot to park car
    //then he will choose capacity with 0/2
    @Test
    void should_select_second_parking_lot() {

        ParkingLot parkingLot1 = new ParkingLot(5);
        ParkingLot parkingLot2 = new ParkingLot(2);
        parkingLot1.park(new ParkingTicket(parkingLot1), new Car());

        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot1, parkingLot2));
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotCenter);

        ParkingLot selectedParkingLot = superSmartParkingBoy.selectParkingLot().get();

        assertSame(selectedParkingLot, parkingLot2);
    }

    //given 2 parking lots in parking center with both full and assigned to SuperSmartParkingBoy
    //when SuperSmartParkingBoy select parking lot to park car
    //then car will not parked
    @Test
    void should_not_park_cars_to_parking_lot_if_there_is_not_enough_position_on_both_parking_lot() {
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot(0);
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot1, parkingLot2));
        ParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLotCenter);

        superSmartParkingBoy.park(new Car());

        assertNull(superSmartParkingBoy.park(new Car()));
    }

}
