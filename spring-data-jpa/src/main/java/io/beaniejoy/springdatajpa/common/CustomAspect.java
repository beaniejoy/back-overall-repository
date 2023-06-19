package io.beaniejoy.springdatajpa.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Aspect
@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomAspect {
    @Around("@annotation(io.beaniejoy.springdatajpa.common.CustomAnnotation)")
    public Object testAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(">>> TestAspect START");
        Object result = joinPoint.proceed();
        log.info(">>> TestAspect END");

        throw new RuntimeException("aop exception");

//        return result;
    }

    @Around("@annotation(io.beaniejoy.springdatajpa.common.CustomAnnotation2)")
    public Object testAround2(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(">>> TestAspect START");
        Object result = joinPoint.proceed();
        log.info(">>> TestAspect END");

        throw new IOException("aop exception");

//        return result;
    }
}
