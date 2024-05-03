package com.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// check the add/remove passenger by providing tests for two flight types and two passenger type
class AirportTest {

    @DisplayName("Given there is an economy flight")
    @Nested
    class EconomyFlightTest {
        private Flight economyFlight;
        private Passenger mike;
        private Passenger james;

        @BeforeEach
        void setUp() {
            economyFlight = new EconomyFlight("1");
            james = new Passenger("James", true);
            mike = new Passenger("Mike", false);
        }

        @DisplayName("When we have a regular passenger")
        @Nested
        class RegularPassenger {
            @Test
            @DisplayName("Then you can add and remove him from an economy flight")
            void testEconomyFlightRegularPassenger() {
                assertAll(
                        "Verify all conditions for a regular passenger and an economy flight",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertEquals(true, economyFlight.addPassenger(mike)),
                        () -> assertEquals(1, economyFlight.getPassengersList().size()),
                        () -> assertEquals("Mike", economyFlight.getPassengersList().get(0).getName()),
                        () -> assertEquals(true, economyFlight.removePassenger(mike)),
                        () -> assertEquals(0, economyFlight.getPassengersList().size())
                );
            }

        }

        @Nested
        @DisplayName("When we have a VIP passenger")
        class VipPassenger {
            @Test
            @DisplayName("Then you can add him but cannot remove him from an economy flight")
            public void testEconomyFlightVipPassenger() {
                assertAll("Verify all conditions for a VIP passenger and an economy flight",
                        () -> assertEquals("1", economyFlight.getId()),
                        () -> assertEquals(true, economyFlight.addPassenger(james)),
                        () -> assertEquals(1, economyFlight.getPassengersList().size()),
                        () -> assertEquals("James", economyFlight.getPassengersList().get(0).getName()),
                        () -> assertEquals(false, economyFlight.removePassenger(james)),
                        () -> assertEquals(1, economyFlight.getPassengersList().size())
                );
            }
        }
    }

    @DisplayName("Given there is an business flight")
    @Nested
    class BusinessFlightTest {
        Passenger mike;
        Passenger james;
        private Flight businessFlight;

        @BeforeEach
        void setUp() {
            businessFlight = new BusinessFlight("2");
            mike = new Passenger("Mike", false);
            james = new Passenger("James", true);
        }
        @Nested
        @DisplayName("WHen we have a regular passenger")
        class RegularPassenger {
            @Test
            @DisplayName("Then you cannot add or remove him from a business fligh")
            void testBusinessFLightRegularPassenger() {
                assertAll(
                  "Verify all conditions for a regular passenger and a business flight",
                        ()-> assertEquals(false, businessFlight.addPassenger(mike)),
                        ()-> assertEquals(0, businessFlight.getPassengersList().size()),
                        ()-> assertEquals(false, businessFlight.removePassenger(mike)),
                        ()-> assertEquals(0, businessFlight.getPassengersList().size())
                );
            }
        }

        @Nested
        @DisplayName("When we have a VIP passenger")
        class VipPassenger {
            @Test
            @DisplayName("Then you can add him but cannot remove him from a business flight")
            void testBusinessFlightVipPassenger() {
                assertAll(
                        "Verify all conditions for a VIP passenger and a business flight",
                        ()-> assertEquals(true, businessFlight.addPassenger(james)),
                        ()-> assertEquals(1, businessFlight.getPassengersList().size()),
                        ()-> assertEquals(false, businessFlight.removePassenger(james)),
                        ()-> assertEquals(1, businessFlight.getPassengersList().size())
                );
            }
        }
    }

    @Nested
    @DisplayName("Given there is a premium flight")
    class PremiumFlightTest {
        private Flight premiumFlight;
        private Passenger mike;
        private Passenger james;

        @BeforeEach
        void setUp() {
            premiumFlight = new PremiumFlight("3");
            mike = new Passenger("Mike", false);
            james = new Passenger("James", true);
        }

        @Nested
        @DisplayName("When we have a regular passenger")
        class RegularPassenger {
            @Test
            @DisplayName("Then you cannot add or remove him from a premium flight")
            public void testPremiumFlightRegularPassenger() {
                assertAll("Verify all conditions for a regular passenger and a premium flight",
                        () -> assertEquals(false, premiumFlight.addPassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengersList().size()),
                        () -> assertEquals(false, premiumFlight.removePassenger(mike)),
                        () -> assertEquals(0, premiumFlight.getPassengersList().size())
                );
            }
        }

        @Nested
        @DisplayName("When we have a VIP passenger")
        class VipPassenger {

            @Test
            @DisplayName("Then you can add and remove him from a premium flight")
            public void testPremiumFlightVipPassenger() {
                assertAll("Verify all conditions for a VIP passenger and a premium flight",
                        () -> assertEquals(true, premiumFlight.addPassenger(james)),
                        () -> assertEquals(1, premiumFlight.getPassengersList().size()),
                        () -> assertEquals(true, premiumFlight.removePassenger(james)),
                        () -> assertEquals(0, premiumFlight.getPassengersList().size())
                );
            }
        }
    }
}
