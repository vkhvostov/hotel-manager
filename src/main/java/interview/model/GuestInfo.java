package interview.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents guest info, so far consisted from acceptable price that a client would like to pay per night in the hotel
 */
public class GuestInfo {

    private final BigDecimal acceptablePrice;

    public GuestInfo(BigDecimal acceptablePrice) {
        this.acceptablePrice = acceptablePrice;
    }

    public BigDecimal getAcceptablePrice() {
        return acceptablePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestInfo guestInfo = (GuestInfo) o;
        return Objects.equals(acceptablePrice, guestInfo.acceptablePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acceptablePrice);
    }

    @Override
    public String toString() {
        return "GuestInfo{" +
                "acceptablePrice=" + acceptablePrice +
                '}';
    }
}
