package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertSame;

class SuperSmartParkingBoyFacts {

    //given 2 parking lots in parking center that capacity are 1/5  and 0/2 and assigned to smart parking boy
    //when parkingBoy select parking lot to park car
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

}
