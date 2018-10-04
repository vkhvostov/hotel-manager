package interview.controller;

import interview.model.GuestInfo;
import interview.model.Profit;
import interview.model.RoomAvailability;
import interview.provider.GuestInfoProvider;
import interview.service.ProfitService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
 * Hotel controller responsible for providing profit from the current hotel setup, etc
 */
@RestController
public class HotelController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final GuestInfoProvider guestInfoProvider;
    private final ProfitService profitService;

    @Autowired
    public HotelController(@Qualifier("httpGuestInfoProvider") final GuestInfoProvider guestInfoProvider,
                           final ProfitService profitService) {
        this.guestInfoProvider = guestInfoProvider;
        this.profitService = profitService;
    }

    @RequestMapping(value = "/profit", method = RequestMethod.POST)
    public Profit profit(@RequestBody final RoomAvailability roomAvailability) {
        logger.info("Received request to calculate profit for room availability {}", roomAvailability);
        final List<BigDecimal> guestInfo = guestInfoProvider.retrieveGuestInfo().stream()
                .map(GuestInfo::getAcceptablePrice)
                .collect(Collectors.toList());
        return profitService.calculateProfit(roomAvailability, guestInfo);
    }
}
