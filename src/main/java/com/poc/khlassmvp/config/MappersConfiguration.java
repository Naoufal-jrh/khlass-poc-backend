package com.poc.khlassmvp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MappersConfiguration {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
