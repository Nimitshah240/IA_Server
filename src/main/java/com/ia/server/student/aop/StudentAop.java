package com.ia.server.student.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class StudentAop {
    private static final Logger logger = LoggerFactory.getLogger(StudentAop.class);


    @Pointcut("execution(* com.ia.server.student.*.*.*(..))")
    public void allMethods() {
    }

    @Before("allMethods()")
    public void allMethodsBeforeLogger(JoinPoint jp) throws Throwable {
//        System.out.println("Before executing method : " + jp.getSignature().toLongString());
        logger.info("Before : " + jp.getTarget().getClass());
    }

    @AfterReturning(pointcut = "allMethods()", returning = "result")
    public void allMethodsAfterLogger(JoinPoint jp, Object result) {
//        System.out.println("After executing method : " + jp.getTarget().getClass() + " : " + result);
        logger.info("After : " + jp.getTarget().getClass());
    }

    @AfterThrowing(pointcut = "allMethods()", throwing = "error")
    public void allMethodAfterThrowingLog(JoinPoint jp, Throwable error) {
//        System.out.println("⚠️ Exception in method: " + jp.getSignature().toShortString());
//        System.out.println("⚠️ Exception message: " + error.getMessage());
        logger.error("🚨 Exception in {}: {}", jp.getSignature().toShortString(), error.getMessage());
    }
}
