package com.ia.server.master.aop;

import com.ia.server.base.dto.BaseExamQuestionDto;
import com.ia.server.student.aop.StudentAop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Aspect
@Component
public class MasterAop {

    private static final Logger logger = LoggerFactory.getLogger(MasterAop.class);

    @Pointcut("execution(* com.ia.server.master.*.*.*(..))")
    public void allMethods() {
    }

    @Before("allMethods()")
    public void allMethodsBeforeLogger(JoinPoint jp) throws Throwable {
        logger.info("Before : " + jp.getTarget().getClass());
    }

    @AfterReturning(pointcut = "allMethods()", returning = "result")
    public void allMethodsAfterLogger(JoinPoint jp, Object result) {
        logger.info("After : " + jp.getTarget().getClass());
    }

    @AfterThrowing(pointcut = "allMethods()", throwing = "error")
    public void allMethodAfterThrowingLog(JoinPoint jp, Throwable error) {
        logger.error("🚨 Exception in {}: {}", jp.getSignature().toShortString(), error.getMessage());
    }

    @Around("execution(* com.ia.server.master.controller.MasterController.getAllStudentData(..))&&args(username,password)")
    public ResponseEntity<?> securityCheck(ProceedingJoinPoint pjp, String username, String password) {
        try {

            logger.info("User : \"{}\" trying to get master data with password : \"{}\"", username, password);
            if (username.equals("Nimit") && password.equals("Shah")) {
                return (ResponseEntity<?>) pjp.proceed();
            } else {
                logger.info("User : \"{}\" failed to get master data with password : \"{}\"", username, password);
                return ResponseEntity.ok("\"Invalid User\"");
            }

        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

    }
}
