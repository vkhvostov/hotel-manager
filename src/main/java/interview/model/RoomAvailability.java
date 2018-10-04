package interview.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * Entity represents the hotel room availability
 */
public class RoomAvailability {

    @JsonProperty("premium_availability")
    private final int premium;
    @JsonProperty("economy_availability")
    private final int economy;

    public RoomAvailability(final int premium, final int economy) {
        this.premium = premium;
        this.economy = economy;
    }

    public int getPremium() {
        return premium;
    }

    public int getEconomy() {
        return economy;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RoomAvailability that = (RoomAvailability) o;
        return premium == that.premium &&
                economy == that.economy;
    }

    @Override
    public int hashCode() {
        return Objects.hash(premium, economy);
    }

    @Override
    public String toString() {
        return "RoomAvailability{" +
                "premium=" + premium +
                ", economy=" + economy +
                '}';
    }
}
