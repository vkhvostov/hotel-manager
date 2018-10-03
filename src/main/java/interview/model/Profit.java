package interview.model;

import java.math.BigDecimal;

/**
 * Created on 03.10.18
 * TODO: Add comment
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
}
