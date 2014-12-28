package kombucha.testutils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@EnableAutoConfiguration
@ImportResource({"/integration/test_data_integration.xml", "/integration/integration.xml"})
public class KombuchaIndicatorServiceWithHistoricalData {
	
    public static void main(String[] args) {
        SpringApplication.run(KombuchaIndicatorServiceWithHistoricalData.class, args);
    }


}