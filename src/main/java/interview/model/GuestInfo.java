package interview.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created on 03.10.18
 * TODO: Add comment
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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
