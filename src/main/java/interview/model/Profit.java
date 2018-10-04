package interview.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents financial profit for the hotel from premium and economy rooms
 */
public class Profit {

    private final BigDecimal premium;
    private final BigDecimal economy;

    public Profit(BigDecimal premium, BigDecimal economy) {
        this.premium = premium;
        this.economy = economy;
    }

    public BigDecimal getPremium() {
        return premium;
    }

    public BigDecimal getEconomy() {
        return economy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profit profit = (Profit) o;
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
