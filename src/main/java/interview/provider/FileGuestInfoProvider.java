package interview.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import interview.model.GuestInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Guest info provider based on receiving data from predefined local file
 */
@Component
public class FileGuestInfoProvider implements GuestInfoProvider {

    private static final String FILE_PATH = "src/main/resources/hotel_guests.json";

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public List<GuestInfo> retrieveGuestInfo() {
        try {
            logger.info("Attempt to get guest info from the file [{}]", FILE_PATH);
            final byte[] encoded = Files.readAllBytes(Paths.get(FILE_PATH));
            final String content = new String(encoded, Charset.defaultCharset());
            final ObjectMapper mapper = new ObjectMapper();
            final BigDecimal[] clientAcceptablePrices = mapper.readValue(content, BigDecimal[].class);

            logger.info("Guest info is successfully retrieved from the file");
            return Arrays.stream(clientAcceptablePrices).map(GuestInfo::new).collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("Failed to retrieve or parse guest info from the file", e);
            return Collections.emptyList();
        }
    }
}
