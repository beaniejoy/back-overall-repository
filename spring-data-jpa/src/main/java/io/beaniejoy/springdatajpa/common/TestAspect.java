package io.beaniejoy.springdatajpa.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TestAspect {
    @Around("@annotation(io.beaniejoy.springdatajpa.common.TestAnnotation)")
    public Object testAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info(">>> TestAspect START");
        Object result = joinPoint.proceed();
        log.info(">>> TestAspect END");

        return result;
    }
}
