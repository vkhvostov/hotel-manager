package interview.model;

import java.util.Objects;

/**
 * Entity represents the hotel room availability
 */
public class RoomAvailability {

    private final int premium;
    private final int economy;

    // TODO: remove!
    public RoomAvailability() {
        this.premium = 0;
        this.economy = 0;
    }

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
