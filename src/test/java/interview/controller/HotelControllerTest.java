package interview.controller;

import interview.model.GuestInfo;
import interview.model.Profit;
import interview.model.RoomAvailability;
import interview.provider.GuestInfoProvider;
import interview.service.ProfitService;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class HotelControllerTest {

    private final GuestInfoProvider provider = Mockito.mock(GuestInfoProvider.class);
    private final ProfitService profitService = Mockito.mock(ProfitService.class);
    private final HotelController controller = new HotelController(provider, profitService);

    private final Profit profit = new Profit(0, 1, BigDecimal.ZERO, BigDecimal.TEN);

    @Before
    public void setUp() throws Exception {
        final List<GuestInfo> guestInfo = Lists.newArrayList(new GuestInfo(BigDecimal.TEN));
        Mockito.when(provider.retrieveGuestInfo()).thenReturn(guestInfo);
        Mockito.when(profitService.calculateProfit(any(), any())).thenReturn(profit);
    }

    @Test
    public void callProfitEndpoint() {
        final Profit actualProfit = controller.profit(new RoomAvailability(0, 1));

        Assert.assertEquals(profit, actualProfit);
    }
}