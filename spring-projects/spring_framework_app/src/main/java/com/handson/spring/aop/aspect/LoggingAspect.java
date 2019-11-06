/**
 * 
 */
package com.handson.spring.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author sveera
 *
 */
@Aspect
@Configuration
public class LoggingAspect {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/*
	 * @Before(value =
	 * "com.handson.spring.aop.pointcut.PoingCuts.serviceLayerPointCut()") public
	 * void beforeLoggingAdvice(JoinPoint joinPoint) throws Throwable {
	 * logger.info(String.format("Entering method execution - %s",
	 * joinPoint.toLongString())); }
	 */

	@Around(value = "com.handson.spring.aop.pointcut.PoingCuts.serviceLayerPointCut()")
	public Object aroundLoggingAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		logger.info(String.format("Entering method execution - %s", proceedingJoinPoint.toLongString()));
		Object result=proceedingJoinPoint.proceed();
		logger.info(String.format("Leaving method execution - %s", proceedingJoinPoint.toLongString()));
		return result;
	}

}
