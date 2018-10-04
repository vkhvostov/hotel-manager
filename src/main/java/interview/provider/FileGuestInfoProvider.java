package interview.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import interview.model.GuestInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final String filePath;

    @Autowired
    public FileGuestInfoProvider(@Value("${guest.info.file}") final String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<GuestInfo> retrieveGuestInfo() {
        try {
            logger.info("Attempt to get guest info from the file [{}]", filePath);
            final byte[] encoded = Files.readAllBytes(Paths.get(filePath));
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
