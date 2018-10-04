package interview.controller;

import interview.model.GuestInfo;
import interview.model.Profit;
import interview.model.RoomAvailability;
import interview.provider.GuestInfoProvider;
import interview.service.ProfitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 03.10.18
 * TODO: Add comment
 */
@RestController
public class HotelController {

    private final GuestInfoProvider guestInfoProvider;
    private final ProfitService profitService;

    @Autowired
    public HotelController(@Qualifier("httpGuestInfoProvider") GuestInfoProvider guestInfoProvider, ProfitService profitService) {
        this.guestInfoProvider = guestInfoProvider;
        this.profitService = profitService;
    }

    @RequestMapping(value = "/profit", method = RequestMethod.POST)
    public Profit profit(@RequestBody RoomAvailability roomAvailability) {
        final List<BigDecimal> guestInfo = guestInfoProvider.retrieveGuestInfo().stream()
                .map(GuestInfo::getAcceptablePrice)
                .collect(Collectors.toList());
        return profitService.calculateProfit(roomAvailability, guestInfo);
    }
}
