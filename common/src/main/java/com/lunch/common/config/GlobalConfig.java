package com.lunch.common.config;

import org.springframework.context.annotation.Bean;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.context.annotation.Configuration;

/**
 * GlobalConfig
 *
 * @author torrisli
 * @date 2022/2/13
 * @Description: GlobalConfig
 */
@Configuration
public class GlobalConfig {

    @Bean("projectValidator")
    public Validator getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        return factory.getValidator();
    }
}
