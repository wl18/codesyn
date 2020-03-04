package com.example.readconfigproperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @Author: weilong
 * @Date: 2020/3/4 18:37
 */

@Component
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "my-profile")
@Validated
public class ProfileProperties {
    @NotEmpty
    private String name;

    @Email
    @NotEmpty
    private String email;

    private Boolean handsome = Boolean.TRUE;
}
