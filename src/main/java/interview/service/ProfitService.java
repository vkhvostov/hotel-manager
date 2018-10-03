package interview.service;

import interview.model.Profit;
import interview.model.RoomAvailability;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created on 03.10.18
 * TODO: Add comment
 */
@Service
public class ProfitService {

    private final BigDecimal premiumThreshold = BigDecimal.valueOf(100);

    public Profit calculateProfit(final RoomAvailability roomAvailability, final List<BigDecimal> guestInfo) {
        final int availabilityPremium = roomAvailability.getPremium();
        final int availabilityEconomy = roomAvailability.getEconomy();

        Assert.isTrue(availabilityPremium >= 0, "Availability of premium rooms should not be negative");
        Assert.isTrue(availabilityEconomy >= 0, "Availability of economy rooms should not be negative");

        final List<BigDecimal> premiumClients = guestInfo.stream().filter(g -> g.compareTo(premiumThreshold) >= 0).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        final List<BigDecimal> economyClients = guestInfo.stream().filter(g -> g.compareTo(premiumThreshold) < 0).sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        if (availabilityPremium > premiumClients.size() && availabilityEconomy < economyClients.size()) {
            // upgrade some economy clients to premium level
            final int freePremiumRooms = availabilityPremium - premiumClients.size();
            final List<BigDecimal> upgradedEconomyClients = economyClients.stream().limit(freePremiumRooms).collect(Collectors.toList());
            economyClients.removeAll(upgradedEconomyClients);
            premiumClients.addAll(upgradedEconomyClients);
        }

        final BigDecimal premiumProfit = premiumClients.stream().limit(availabilityPremium).reduce(BigDecimal.ZERO, BigDecimal::add);
        final BigDecimal economyProfit = economyClients.stream().limit(availabilityEconomy).reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Profit(premiumProfit, economyProfit);
    }
}
