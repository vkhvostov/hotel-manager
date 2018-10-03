package interview.model;

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
}
