package bs23.practical.usermanagement.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfig {

    @Bean
    public Gson jsonConverter(){
        return new Gson();
    }
}
