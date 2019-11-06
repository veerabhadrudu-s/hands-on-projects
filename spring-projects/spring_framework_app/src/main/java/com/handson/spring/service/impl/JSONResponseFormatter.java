/**
 * 
 */
package com.handson.spring.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JSONResponseFormatter {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final GsonBuilder gsonBuilder = new GsonBuilder();

	@PostConstruct
	public void init() {
		logger.info(String.format("JSON Response Formatter hashcode - %s in Post Construct", hashCode()));
	}

	public String createJSONResponse(String message) {
		logger.info("JSON Response Formatter instance hashcode is - " + hashCode());
		logger.info("GsonBuilder instance hashcode is 1st time - " + gsonBuilder.hashCode());
		logger.info("GsonBuilder instance hashcode is 2nd time - " + gsonBuilder.hashCode());
		Gson gson = gsonBuilder.create();
		return gson.toJson(new ResponseMessage(message));
	}

	@PreDestroy
	public void destroy() {
		logger.info(String.format("JSON Response Formatter hashcode - %s in Pre Destroy", hashCode()));
	}

	public class ResponseMessage {

		private final String msg;

		public ResponseMessage(String msg) {
			super();
			this.msg = msg;
		}

		public String getMsg() {
			return msg;
		}

	}

}