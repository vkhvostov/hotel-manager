package interview.provider;

import interview.model.GuestInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HttpGuestInfoProviderTest {

    private final RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
    private final String url = "some/url";

    private final BigDecimal[] data = new BigDecimal[]{BigDecimal.valueOf(55), BigDecimal.valueOf(66)};

    @Before
    public void setUp() throws Exception {
        Mockito.when(restTemplate.getForObject(url, BigDecimal[].class)).thenReturn(data);
    }

    @Test
    public void retrieveSuccessfullyGuestInfo() {
        final HttpGuestInfoProvider provider = new HttpGuestInfoProvider(restTemplate, url);
        final List<GuestInfo> actualGuestInfo = provider.retrieveGuestInfo();
        final List<GuestInfo> expectedGuestInfo = Arrays.stream(data).map(GuestInfo::new).collect(Collectors.toList());

        Assert.assertEquals(expectedGuestInfo, actualGuestInfo);
    }

    @Test
    public void retrieveUnsuccessfullyGuestInfo() {
        final HttpGuestInfoProvider provider = new HttpGuestInfoProvider(restTemplate, "wrong/url");
        final List<GuestInfo> actualGuestInfo = provider.retrieveGuestInfo();

        Assert.assertEquals(Collections.emptyList(), actualGuestInfo);
    }
}