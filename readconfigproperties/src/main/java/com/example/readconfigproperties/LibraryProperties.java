package com.example.readconfigproperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: weilong
 * @Date: 2020/3/4 17:06
 */

@Component
@ConfigurationProperties(prefix = "library")
@Getter
@Setter
@ToString
public class LibraryProperties {
    private String location;
    private List<Book> books;

    @Setter
    @Getter
    @ToString
    static class Book {
        String name;
        String description;
    }
}
