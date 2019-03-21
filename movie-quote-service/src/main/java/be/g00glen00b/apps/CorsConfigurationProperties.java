package be.g00glen00b.apps;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "cors")
public class CorsConfigurationProperties {
    /**
     * List of allowed origins for cross origin requests
     */
    private String[] allowedOrigins = new String[]{};

}
