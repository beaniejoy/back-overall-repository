package io.beaniejoy.resttemplatebasic.service;

import io.beaniejoy.resttemplatebasic.entity.ExampleGetter;
import org.springframework.stereotype.Service;

@Service
public class ExampleGetterService {
    public ExampleGetter getExampleGetter() {
        return new ExampleGetter(1L, "beanie", "beanie@example.com", "address1");
    }
}
