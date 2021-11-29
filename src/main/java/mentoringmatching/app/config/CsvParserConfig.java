package mentoringmatching.app.config;

import akhandanyan.csv.CsvParser;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CsvParserConfig {

    @Bean
    public CsvParser csvParser() {
        return new CsvParser();
    }
}
