package io.beaniejoy.resetpwdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ResetpwOutboxSchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResetpwOutboxSchedulerApplication.class, args);
    }

}
