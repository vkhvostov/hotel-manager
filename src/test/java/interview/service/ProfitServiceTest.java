package interview.service;

import interview.model.Profit;
import interview.model.RoomAvailability;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProfitServiceTest {

    private final ProfitService profitService = new ProfitService(BigDecimal.valueOf(100));

    private final List<BigDecimal> guestInfo = Lists.newArrayList(23, 45, 155, 374, 22, 99, 100, 101, 115, 209).stream().map(BigDecimal::new).collect(Collectors.toList());

    // The test case when number of available rooms for both type of clients is not enough
    @Test
    public void calculateProfitWhenNotEnoughPremiumAndEconomy() {
        final RoomAvailability roomAvailability = new RoomAvailability(3, 3);
        final Profit actualProfit = profitService.calculateProfit(roomAvailability, guestInfo);
        final Profit expectedProfit = new Profit(3, 3, BigDecimal.valueOf(738), BigDecimal.valueOf(167));

        Assert.assertEquals(expectedProfit, actualProfit);
    }

    // The test case when number of available rooms for both type of clients is more than needed
    @Test
    public void calculateProfitWhenRoomsMoreThanNeeded() {
        final RoomAvailability roomAvailability = new RoomAvailability(7, 5);
        final Profit actualProfit = profitService.calculateProfit(roomAvailability, guestInfo);
        final Profit expectedProfit = new Profit(6, 4, BigDecimal.valueOf(1054), BigDecimal.valueOf(189));

        Assert.assertEquals(expectedProfit, actualProfit);
    }

    // The test case when number of available rooms for premium clients is not enough but for economy clients is more than needed
    @Test
    public void calculateProfitWhenEconomyRoomsMoreThanNeededPremiumNotEnough() {
        final RoomAvailability roomAvailability = new RoomAvailability(2, 7);
        final Profit actualProfit = profitService.calculateProfit(roomAvailability, guestInfo);
        final Profit expectedProfit = new Profit(2, 4, BigDecimal.valueOf(583), BigDecimal.valueOf(189));

        Assert.assertEquals(expectedProfit, actualProfit);
    }

    // The test case when number of available rooms for economy clients is not enough but for premium clients is more than needed
    @Test
    public void calculateProfitWhenPremiumRoomsMoreThanNeededEconomyNotEnough() {
        final RoomAvailability roomAvailability = new RoomAvailability(7, 1);
        final Profit actualProfit = profitService.calculateProfit(roomAvailability, guestInfo);
        final Profit expectedProfit = new Profit(7, 1, BigDecimal.valueOf(1153), BigDecimal.valueOf(45));

        Assert.assertEquals(expectedProfit, actualProfit);
    }

    // The test case when no available rooms
    @Test
    public void calculateProfitWhenNoAvailableRooms() {
        final RoomAvailability roomAvailability = new RoomAvailability(0, 0);
        final Profit actualProfit = profitService.calculateProfit(roomAvailability, guestInfo);
        final Profit expectedProfit = new Profit(0, 0, BigDecimal.valueOf(0), BigDecimal.valueOf(0));

        Assert.assertEquals(expectedProfit, actualProfit);
    }

    // The test case when no guests
    @Test
    public void calculateProfitWhenNoGuests() {
        final RoomAvailability roomAvailability = new RoomAvailability(10, 10);
        final Profit actualProfit = profitService.calculateProfit(roomAvailability, Collections.emptyList());
        final Profit expectedProfit = new Profit(0, 0, BigDecimal.valueOf(0), BigDecimal.valueOf(0));

        Assert.assertEquals(expectedProfit, actualProfit);
    }

    // The test case when provided availability of economy rooms is negative
    @Test(expected = IllegalArgumentException.class)
    public void calculateProfitWhenEconomyRoomsNegative() {
        final RoomAvailability roomAvailability = new RoomAvailability(10, -10);
        profitService.calculateProfit(roomAvailability, Collections.emptyList());
    }

    // The test case when provided availability of premium rooms is negative
    @Test(expected = IllegalArgumentException.class)
    public void calculateProfitWhenPremiumRoomsNegative() {
        final RoomAvailability roomAvailability = new RoomAvailability(-10, 10);
        profitService.calculateProfit(roomAvailability, Collections.emptyList());
    }

    // The test case when provided guest info has a decimal values
    @Test
    public void calculateProfitWhenGuestInfoDecimal() {
        final RoomAvailability roomAvailability = new RoomAvailability(3, 2);
        final ArrayList<BigDecimal> guestInfo =
                Lists.newArrayList(BigDecimal.valueOf(100.55), BigDecimal.valueOf(221.37), BigDecimal.valueOf(157.72),
                        BigDecimal.valueOf(33.93), BigDecimal.valueOf(99.99), BigDecimal.valueOf(58.88));
        final Profit actualProfit = profitService.calculateProfit(roomAvailability, guestInfo);
        final Profit expectedProfit = new Profit(3, 2, BigDecimal.valueOf(479.64), BigDecimal.valueOf(158.87));

        Assert.assertEquals(expectedProfit, actualProfit);
    }
}