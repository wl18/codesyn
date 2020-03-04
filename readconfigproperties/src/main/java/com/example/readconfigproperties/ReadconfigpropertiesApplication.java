package com.example.readconfigproperties;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import javax.naming.spi.InitialContextFactory;

@SpringBootApplication
public class ReadconfigpropertiesApplication implements InitializingBean {

    private final ProfileProperties profileProperties;
    private final LibraryProperties libraryProperties;
    private final Website website;
    @Value("${wuhan2020}")
    String wuhan2020;

    public ReadconfigpropertiesApplication(LibraryProperties library, ProfileProperties profileProperties, Website website){
        this.libraryProperties = library;
        this.profileProperties = profileProperties;
        this.website = website;
    }

    public static void main(String[] args) {
        SpringApplication.run(ReadconfigpropertiesApplication.class, args);
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println(wuhan2020);
        System.out.println(website.getUrl());
        System.out.println(libraryProperties.getLocation());
        System.out.println(libraryProperties.getBooks());
        System.out.println(profileProperties.toString());
    }
}
