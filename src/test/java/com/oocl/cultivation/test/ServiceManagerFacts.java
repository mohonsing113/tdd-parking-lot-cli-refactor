package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ServiceManagerFacts {
    @Test
    void should_park_a_car_to_a_parking_lot_and_get_it_back() {
        ParkingLot parkingLot = new ParkingLot();
        ServiceManager serviceManager = new ServiceManager(Arrays.asList(parkingLot));
        Car car = new Car();

        ParkingTicket ticket = serviceManager.park(car);
        Car fetched = serviceManager.fetch(ticket);

        assertSame(fetched, car);
    }

    @Test
    void should_park_multiple_cars_to_a_parking_lot_and_get_them_back() {
        ParkingLot parkingLot = new ParkingLot();
        ServiceManager serviceManager = new ServiceManager(Arrays.asList(parkingLot));
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingTicket firstTicket = serviceManager.park(firstCar);
        ParkingTicket secondTicket = serviceManager.park(secondCar);

        Car fetchedByFirstTicket = serviceManager.fetch(firstTicket);
        Car fetchedBySecondTicket = serviceManager.fetch(secondTicket);

        assertSame(firstCar, fetchedByFirstTicket);
        assertSame(secondCar, fetchedBySecondTicket);
    }

    @Test
    void should_not_fetch_any_car_once_ticket_is_wrong() {
        ParkingLot parkingLot = new ParkingLot();
        ServiceManager serviceManager = new ServiceManager(Arrays.asList(parkingLot));
        Car car = new Car();
        ParkingTicket wrongTicket = new ParkingTicket(new ParkingLot());

        ParkingTicket ticket = serviceManager.park(car);

        assertNull(serviceManager.fetch(wrongTicket));
        assertSame(car, serviceManager.fetch(ticket));
    }

    @Test
    void should_query_message_once_the_ticket_is_wrong() {
        ParkingLot parkingLot = new ParkingLot();
        ServiceManager serviceManager = new ServiceManager(Arrays.asList(parkingLot));
        ParkingTicket wrongTicket = new ParkingTicket(parkingLot);

        serviceManager.fetch(wrongTicket);
        String message = serviceManager.getLastErrorMessage();

        assertEquals("Unrecognized parking ticket.", message);
    }

    @Test
    void should_clear_the_message_once_the_operation_is_succeeded() {
        ParkingLot parkingLot = new ParkingLot();
        ServiceManager serviceManager = new ServiceManager(Arrays.asList(parkingLot));
        ParkingTicket wrongTicket = new ParkingTicket(parkingLot);

        serviceManager.fetch(wrongTicket);
        assertNotNull(serviceManager.getLastErrorMessage());

        ParkingTicket ticket = serviceManager.park(new Car());
        assertNotNull(ticket);
        assertNull(serviceManager.getLastErrorMessage());
    }

    @Test
    void should_not_fetch_any_car_once_ticket_is_not_provided() {
        ServiceManager serviceManager = new ServiceManager(Arrays.asList(new ParkingLot()));
        Car car = new Car();

        ParkingTicket ticket = serviceManager.park(car);

        assertNull(serviceManager.fetch(null));
        assertSame(car, serviceManager.fetch(ticket));
    }

    @Test
    void should_query_message_once_ticket_is_not_provided() {
        ServiceManager serviceManager = new ServiceManager(Arrays.asList(new ParkingLot()));

        serviceManager.fetch(null);

        assertEquals(
            "Please provide your parking ticket.",
            serviceManager.getLastErrorMessage());
    }

    @Test
    void should_not_fetch_any_car_once_ticket_has_been_used() {
        ServiceManager serviceManager = new ServiceManager(Arrays.asList(new ParkingLot()));
        Car car = new Car();

        ParkingTicket ticket = serviceManager.park(car);
        serviceManager.fetch(ticket);

        assertNull(serviceManager.fetch(ticket));
    }

    @Test
    void should_query_error_message_for_used_ticket() {
        ParkingLot parkingLot = new ParkingLot();
        ServiceManager serviceManager = new ServiceManager(Arrays.asList(parkingLot));
        Car car = new Car();

        ParkingTicket ticket = serviceManager.park(car);
        serviceManager.fetch(ticket);
        serviceManager.fetch(ticket);

        assertEquals(
            "Unrecognized parking ticket.",
            serviceManager.getLastErrorMessage()
        );
    }

    @Test
    void should_not_park_cars_to_parking_lot_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ServiceManager serviceManager = new ServiceManager(Arrays.asList(parkingLot));

        serviceManager.park(new Car());

        assertNull(serviceManager.park(new Car()));
    }

    @Test
    void should_get_message_if_there_is_not_enough_position() {
        final int capacity = 1;
        ParkingLot parkingLot = new ParkingLot(capacity);
        ServiceManager serviceManager = new ServiceManager(Arrays.asList(parkingLot));

        serviceManager.park(new Car());
        serviceManager.park(new Car());

        assertEquals("The parking lot is full.", serviceManager.getLastErrorMessage());
    }

    //given full parking lot
    //when parkingBoy fetch one car
    //then he can park a new car
    @Test
    void should_fetch_car_and_remove_from_parking_lot() {
        ParkingLot parkingLot = new ParkingLot(1);
        ServiceManager serviceManager = new ServiceManager(Arrays.asList(parkingLot));
        Car oldCar = new Car();
        ParkingTicket oldTicket = serviceManager.park(oldCar);
        serviceManager.fetch(oldTicket);

        assertNotNull(serviceManager.park(new Car()));
    }
    

    //given 1 parking boy for manager
    //when the service manager order the parking boy to park car
    //then the car will be parked and can get it back
    @Test
    void should_order_to_park_a_car_to_a_parking_lot_and_get_it_back() {
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot);
        ServiceManager serviceManager = new ServiceManager(parkingLots);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        serviceManager.addParkingBoy(parkingBoy);

        Car car = new Car();


        ParkingTicket ticket = serviceManager.orderToPark(parkingBoy, car);
        Car fetched = serviceManager.orderToFetch(parkingBoy, ticket);

        assertSame(fetched, car);
    }

    //given no parking boy for service manager
    //when the service manager order a unknown parking boy to park car
    //then the car will not packed
    @Test
    void should_not_park_the_car() {
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot);
        ServiceManager serviceManager = new ServiceManager(parkingLots);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        Car car = new Car();

        assertNull(serviceManager.orderToPark(parkingBoy, car));
    }

    //given null ticket to the manager for ordering fetching car
    //when the service manager order a  parking boy to park car
    //then the car will not fetched and print the error message
    @Test
    void should_print_the_error_message() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot);
        ServiceManager serviceManager = new ServiceManager(parkingLots);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        serviceManager.addParkingBoy(parkingBoy);

        serviceManager.orderToFetch(parkingBoy, null);

        assertEquals(
                "Please provide your parking ticket."+System.getProperty("line.separator"),
                outContent.toString());
    }

}
