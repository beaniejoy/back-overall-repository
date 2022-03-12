package io.beaniejoy.springframeworkbasic;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class SpringFrameworkBasicApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SpringFrameworkBasicApplication.class, args);

        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));

        BookService bookService = (BookService) context.getBean("bookService");
        System.out.println(bookService.getBookRepository() != null);
    }

}
