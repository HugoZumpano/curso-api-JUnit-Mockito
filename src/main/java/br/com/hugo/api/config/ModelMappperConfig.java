package br.com.hugo.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMappperConfig {
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
