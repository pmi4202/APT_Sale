package com.ssafy.final_project.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionCheckAop {
	private final Logger logger = LoggerFactory.getLogger(ExceptionCheckAop.class);
	
	@Pointcut("execution(* com.ssafy.final_project..*.*(..))")
	public void cut() {
		
	}
	@AfterThrowing(pointcut = "cut()",throwing = "ex")
	public void throwCheckValue(JoinPoint jp,Throwable ex) throws Throwable{
		logger.error("error occured ===================");
		logger.error(ex.getMessage());
		logger.error(ex.getClass().getName());
		logger.error(jp.toShortString());
		logger.error("=================================");
	}
	
}
