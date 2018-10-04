package interview.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents financial profit for the hotel from premium and economy rooms
 */
public class Profit {

    @JsonProperty("premium_profit")
    private final BigDecimal premium;
    @JsonProperty("economy_profit")
    private final BigDecimal economy;

    public Profit(final BigDecimal premium, final BigDecimal economy) {
        this.premium = premium.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        this.economy = economy.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public BigDecimal getEconomy() {
        return economy;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Profit profit = (Profit) o;
        return Objects.equals(premium, profit.premium) &&
                Objects.equals(economy, profit.economy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(premium, economy);
    }

    @Override
    public String toString() {
        return "Profit{" +
                "premium=" + premium +
                ", economy=" + economy +
                '}';
    }
}
