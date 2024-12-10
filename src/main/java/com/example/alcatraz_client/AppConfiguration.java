package com.example.alcatraz_client;

import com.example.alcatraz_client.game.AlcatrazLogic;
import com.example.alcatraz_client.rest.PortStartupRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

@Configuration
public class AppConfiguration  implements EnvironmentAware{

    @Autowired
    private WebServerApplicationContext webServerApplicationContext;

    @Autowired
    private Environment env;

    @Bean
    @Primary
    public PortFetcher getPortFetcher() {
        return new PortFetcher(env);
    }

    @Bean
    public AlcatrazLogic getAlcatrazLogic() {
        return new AlcatrazLogic(getPortFetcher());
    }

    @Bean
    @Primary
    public PortStartupRunner getPortStartupRunner() {
        return new PortStartupRunner(webServerApplicationContext);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }
}
