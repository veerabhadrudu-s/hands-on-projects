/**
 * 
 */
package com.handson.spring.boot.arithmetic.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author sveera
 *
 */
@Component
public class SprinBeanPostProcesser implements BeanPostProcessor {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) {
		logger.trace(String.format("Bean of class %s with %s name Initialized ", bean.getClass().getName(), beanName));
		return bean;
	}
}