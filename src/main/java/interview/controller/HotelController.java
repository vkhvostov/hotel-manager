package interview.controller;

import interview.model.Profit;
import interview.model.RoomAvailability;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created on 03.10.18
 * TODO: Add comment
 */
@RestController
public class HotelController {

    @RequestMapping(value = "/profit", method = RequestMethod.POST)
    public Profit profit(@RequestBody RoomAvailability roomAvailability) {
        final BigDecimal economyProfit = BigDecimal.valueOf(roomAvailability.getEconomy() * 5);
        final BigDecimal premiumProfit = BigDecimal.valueOf(roomAvailability.getPremium() * 10);
        return new Profit(premiumProfit, economyProfit);
    }
}
