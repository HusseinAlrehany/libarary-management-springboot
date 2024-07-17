package com.libararymangementsystem.demo.converter;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class modelmapperconvertor {
    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }
}
