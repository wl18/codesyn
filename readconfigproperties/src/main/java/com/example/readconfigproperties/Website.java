package com.example.readconfigproperties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: weilong
 * @Date: 2020/3/4 18:48
 */

@Component
@PropertySource("classpath:website.properties")
@Getter
@Setter
class Website {
    @Value("${url}")
    private String url;
}
