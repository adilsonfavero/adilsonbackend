package com.example.demo.config;

import java.text.ParseException;

import com.example.demo.services.DBService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("test")
public class TestConfig{

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instanciateDatabase() throws ParseException{
        dbService.instantiateDatabase();
        return true; 
    }


}