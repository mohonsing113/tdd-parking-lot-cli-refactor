package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SmartParkingBoyFacts {


    //given 2 parking lots in parking center that capacity are 12 and 9 and assigned to smart parking boy
    //when parkingBoy select parking lot to park car
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
    //when parkingBoy select parking lot to park car
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

}
