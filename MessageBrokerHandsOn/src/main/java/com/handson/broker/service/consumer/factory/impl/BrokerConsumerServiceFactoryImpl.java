/**
 * 
 */
package com.handson.broker.service.consumer.factory.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.handson.broker.service.consumer.BrokerConsumerService;
import com.handson.broker.service.consumer.factory.BrokerConsumerServiceFactory;

/**
 * @author sveera
 *
 */
public class BrokerConsumerServiceFactoryImpl<V> implements BrokerConsumerServiceFactory<V> {

	private Map<String, BrokerConsumerService<V>> brokerConsumerServices = new ConcurrentHashMap<>();

	public BrokerConsumerServiceFactoryImpl(List<BrokerConsumerService<V>> brokerConsumerServices) {
		for (BrokerConsumerService<V> brokerConsumerService : brokerConsumerServices)
			this.brokerConsumerServices.put(brokerConsumerService.getName(), brokerConsumerService);
	}

	@Override
	public BrokerConsumerService<V> getBrokerConsumerService(String brokerConsumerServiceName) {
		return brokerConsumerServices.get(brokerConsumerServiceName);
	}

	@Override
	public String toString() {
		return "BrokerConsumerServiceFactoryImpl [brokerConsumerServices=" + brokerConsumerServices + "]";
	}

}
