package interview.model;

import java.util.Objects;

/**
 * Created on 03.10.18
 * TODO: Add comment
 */
public class RoomAvailability {

    private final int premium;
    private final int economy;

    // TODO: remove!
    public RoomAvailability() {
        this.premium = 0;
        this.economy = 0;
    }

    public RoomAvailability(int premium, int economy) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomAvailability that = (RoomAvailability) o;
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
