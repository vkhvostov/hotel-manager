package interview.provider;

import com.google.common.collect.Lists;
import interview.model.GuestInfo;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class FileGuestInfoProviderTest {

    @Test
    public void retrieveGuestInfoFromValidFileLocation() {
        final FileGuestInfoProvider provider = new FileGuestInfoProvider("src/test/resources/hotel_guests.json");
        final List<GuestInfo> actualGuestInfo = provider.retrieveGuestInfo();
        final List<GuestInfo> expectedGuestInfo = Lists.newArrayList(new GuestInfo(BigDecimal.valueOf(23)), new GuestInfo(BigDecimal.valueOf(45)), new GuestInfo(BigDecimal.valueOf(155.55)));

        Assert.assertEquals(expectedGuestInfo, actualGuestInfo);
    }

    @Test
    public void retrieveGuestInfoFromNotValidFileLocation() {
        final FileGuestInfoProvider provider = new FileGuestInfoProvider("somewhere/");
        final List<GuestInfo> actualGuestInfo = provider.retrieveGuestInfo();

        Assert.assertEquals(Collections.emptyList(), actualGuestInfo);
    }
}