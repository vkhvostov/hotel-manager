package interview.provider;

import interview.model.GuestInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
 * Created on 03.10.18
 * TODO: Add comment
 */
@Component
public class HttpGuestInfoProvider implements GuestInfoProvider {

    private static final String URL = "https://gist.githubusercontent.com/fjahr/b164a446db285e393d8e4b36d17f4143/raw/75108c09a72a001a985d27b968a0ac5a867e830b/smarthost_hotel_guests.json";

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final RestTemplate restTemplate;

    public HttpGuestInfoProvider() {
        this.restTemplate = new RestTemplate();

        // Make rest template able to parse text/plain as a json
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON));
        restTemplate.getMessageConverters().add(0, converter);
    }

    @Override
    public List<GuestInfo> retrieveGuestInfo() {
        logger.info("Requesting guest info from the provided endpoint");
        final BigDecimal[] response = restTemplate.getForObject(URL, BigDecimal[].class);
        logger.info("Response: " + Arrays.toString(response));
        return response != null ? Arrays.stream(response).map(GuestInfo::new).collect(Collectors.toList()) : Collections.emptyList();
    }
}
