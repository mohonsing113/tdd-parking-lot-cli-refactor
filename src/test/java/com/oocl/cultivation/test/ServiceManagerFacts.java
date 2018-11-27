package com.oocl.cultivation.test;

import com.oocl.cultivation.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ServiceManagerFacts {

    //given 1 parking boy for manager
    //when the service manager order the parking boy to park car
    //then the car will be parked and can get it back
    @Test
    void should_order_to_park_a_car_to_a_parking_lot_and_get_it_back() {
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        ServiceManager serviceManager = new ServiceManager(parkingLots);
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
    void should_park_a_car_to_a_parking_lot_and_get_it_back() {
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        ServiceManager serviceManager = new ServiceManager(parkingLots);

        Car car = new Car();

        assertNull(serviceManager.orderToPark(parkingBoy, car));
    }

    //given null ticket to the manager for ordering fetching car
    //when the service manager order a  parking boy to park car
    //then the car will not fetched and print the error message
    @Test
    void should_prin_the_error_message() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = Arrays.asList(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        ServiceManager serviceManager = new ServiceManager(parkingLots);
        serviceManager.addParkingBoy(parkingBoy);

        serviceManager.orderToFetch(parkingBoy, null);

        assertEquals(
                "Please provide your parking ticket."+System.getProperty("line.separator"),
                outContent.toString());
    }

}
