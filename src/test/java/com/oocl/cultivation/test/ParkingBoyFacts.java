package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ParkingBoyFacts {
    @Test
    void should_park_a_car_to_a_parking_lot_and_get_it_back() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        Car fetched = parkingBoy.fetch(ticket);

        assertSame(fetched, car);
    }

    @Test
    void should_park_multiple_cars_to_a_parking_lot_and_get_them_back() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingTicket firstTicket = parkingBoy.park(firstCar);
        ParkingTicket secondTicket = parkingBoy.park(secondCar);

        Car fetchedByFirstTicket = parkingBoy.fetch(firstTicket);
        Car fetchedBySecondTicket = parkingBoy.fetch(secondTicket);

        assertSame(firstCar, fetchedByFirstTicket);
        assertSame(secondCar, fetchedBySecondTicket);
    }

    @Test
    void should_not_fetch_any_car_once_ticket_is_wrong() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
        Car car = new Car();
        ParkingTicket wrongTicket = new ParkingTicket(new ParkingLot());

        ParkingTicket ticket = parkingBoy.park(car);

        assertNull(parkingBoy.fetch(wrongTicket));
        assertSame(car, parkingBoy.fetch(ticket));
    }

    @Test
    void should_query_message_once_the_ticket_is_wrong() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(new ParkingLot()));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
        ParkingTicket wrongTicket = new ParkingTicket(parkingLot);

        parkingBoy.fetch(wrongTicket);
        String message = parkingBoy.getLastErrorMessage();

        assertEquals("Unrecognized parking ticket.", message);
    }

    @Test
    void should_clear_the_message_once_the_operation_is_succeeded() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(new ParkingLot()));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
        ParkingTicket wrongTicket = new ParkingTicket(parkingLot);

        parkingBoy.fetch(wrongTicket);
        assertNotNull(parkingBoy.getLastErrorMessage());

        ParkingTicket ticket = parkingBoy.park(new Car());
        assertNotNull(ticket);
        assertNull(parkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_fetch_any_car_once_ticket_is_not_provided() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);

        assertNull(parkingBoy.fetch(null));
        assertSame(car, parkingBoy.fetch(ticket));
    }

    @Test
    void should_query_message_once_ticket_is_not_provided() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);

        parkingBoy.fetch(null);

        assertEquals(
            "Please provide your parking ticket.",
            parkingBoy.getLastErrorMessage());
    }

    @Test
    void should_not_fetch_any_car_once_ticket_has_been_used() {
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(new ParkingLot()));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);

        assertNull(parkingBoy.fetch(ticket));
    }

    @Test
    void should_query_error_message_for_used_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        parkingBoy.fetch(ticket);
        parkingBoy.fetch(ticket);

        assertEquals(
            "Unrecognized parking ticket.",
            parkingBoy.getLastErrorMessage()
        );
    }

    @Test
    void should_not_park_cars_to_parking_lot_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);

        parkingBoy.park(new Car());

        assertNull(parkingBoy.park(new Car()));
    }

    @Test
    void should_get_message_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);

        parkingBoy.park(new Car());
        parkingBoy.park(new Car());

        assertEquals("The parking lot is full.", parkingBoy.getLastErrorMessage());
    }


    //given 2 parking lots in parking center that both has space and assigned to parking boy
    //when parkingBoy park car
    //then park to the first parking lot
    @Test
    void should_park_car_to_the_first_parking_lot() {
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot1, parkingLot2));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        Car fetched = parkingBoy.fetch(ticket);

        assertSame(fetched, car);
    }

    //given 2 parking lots in parking center that the first one is full and the second one has space and assigned to parking boy
    //when parkingBoy park car
    //then park to the second parking lot
    @Test
    void should_park_car_to_the_second_parking_lot() {
        ParkingLot parkingLot1 = new ParkingLot(0);
        ParkingLot parkingLot2 = new ParkingLot();
        ParkingLotCenter parkingLotCenter = new ParkingLotCenter(Arrays.asList(parkingLot1, parkingLot2));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotCenter);
        Car car = new Car();

        ParkingTicket ticket = parkingBoy.park(car);
        Car fetched = parkingBoy.fetch(ticket);

        assertSame(fetched, car);
    }

}
