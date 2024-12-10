package com.example.alcatraz_client;

import com.example.alcatraz_client.game.AlcatrazLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AppConfiguration  implements EnvironmentAware {

    @Autowired
    private Environment env;

    @Bean
    public PortFetcher getPortFetcher() {
        return new PortFetcher(env);
    }

    @Bean
    public AlcatrazLogic getAlcatrazLogic() {
        return new AlcatrazLogic(getPortFetcher());
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }
}
