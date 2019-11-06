/**
 * 
 */
package com.handson.spring.aop.pointcut;

import org.aspectj.lang.annotation.Pointcut;

/**
 * @author sveera
 *
 */
public class PoingCuts {

	@Pointcut(value = "execution(* com.handson.spring.service..*.*(..))")
	public void serviceLayerPointCut() {
	}

}
