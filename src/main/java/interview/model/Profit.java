package interview.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents usage and financial profit of premium and economy rooms
 */
public class Profit {

    @JsonProperty("premium_usage")
    private final int premiumUsage;
    @JsonProperty("economy_usage")
    private final int economyUsage;
    @JsonProperty("premium_profit")
    private final BigDecimal premiumProfit;
    @JsonProperty("economy_profit")
    private final BigDecimal economyProfit;

    public Profit(final int premiumUsage, final int economyUsage, final BigDecimal premiumProfit, final BigDecimal economy) {
        this.premiumUsage = premiumUsage;
        this.economyUsage = economyUsage;
        this.premiumProfit = premiumProfit.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        this.economyProfit = economy.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    public int getPremiumUsage() {
        return premiumUsage;
    }

    public int getEconomyUsage() {
        return economyUsage;
    }

    public BigDecimal getPremiumProfit() {
        return premiumProfit;
    }

    public BigDecimal getEconomyProfit() {
        return economyProfit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profit profit = (Profit) o;
        return premiumUsage == profit.premiumUsage &&
                economyUsage == profit.economyUsage &&
                Objects.equals(premiumProfit, profit.premiumProfit) &&
                Objects.equals(economyProfit, profit.economyProfit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(premiumUsage, economyUsage, premiumProfit, economyProfit);
    }

    @Override
    public String toString() {
        return "Profit{" +
                "premiumUsage=" + premiumUsage +
                ", economyUsage=" + economyUsage +
                ", premiumProfit=" + premiumProfit +
                ", economyProfit=" + economyProfit +
                '}';
    }
}
