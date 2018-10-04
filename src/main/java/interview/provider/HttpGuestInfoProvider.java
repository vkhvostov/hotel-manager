package interview.provider;

import interview.model.GuestInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Guest info provider based on rest call to predefined URL
 */
@Component
public class HttpGuestInfoProvider implements GuestInfoProvider {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final RestTemplate restTemplate;
    private final String guestInfoUrl;

    @Autowired
    public HttpGuestInfoProvider(@Value("${guest.info.url}") final String guestInfoUrl) {
        this.guestInfoUrl = guestInfoUrl;
        this.restTemplate = new RestTemplate();

        // Make rest template able to parse text/plain as a json
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
        restTemplate.getMessageConverters().add(0, converter);
    }

    @Override
    public List<GuestInfo> retrieveGuestInfo() {
        logger.info("Requesting guest info from the provided endpoint {}", guestInfoUrl);
        final BigDecimal[] response = restTemplate.getForObject(guestInfoUrl, BigDecimal[].class);
        logger.info("Response: " + Arrays.toString(response));
        return response != null ? Arrays.stream(response).map(GuestInfo::new).collect(Collectors.toList()) : Collections.emptyList();
    }
}
