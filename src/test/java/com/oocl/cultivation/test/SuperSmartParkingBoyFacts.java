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

        ParkingLot parkingLotWithLessSpaceRate = new ParkingLot();
        parkingLotWithLessSpaceRate.park(new ParkingTicket(parkingLotWithLessSpaceRate), new Car());
        ParkingLot parkingLotWithMoreSpaceRate = new ParkingLot();
        
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(Arrays.asList(parkingLotWithMoreSpaceRate, parkingLotWithLessSpaceRate));

        ParkingLot selectedParkingLot = superSmartParkingBoy.selectParkingLot().get();

        assertSame(selectedParkingLot, parkingLotWithMoreSpaceRate);
    }

}
