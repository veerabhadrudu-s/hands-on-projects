/**
 * 
 */
package com.handson.broker.service.producer.factory.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.handson.broker.service.producer.BrokerProducerService;
import com.handson.broker.service.producer.factory.BrokerProducerServiceFactory;

/**
 * @author sveera
 *
 */
public class BrokerProducerServiceFactoryImpl<V> implements BrokerProducerServiceFactory<V> {

	private Map<String, BrokerProducerService<V>> brokerProducerServices = new ConcurrentHashMap<>();

	public BrokerProducerServiceFactoryImpl(List<BrokerProducerService<V>> brokerProducerServices) {
		for (BrokerProducerService<V> brokerProducerService : brokerProducerServices)
			this.brokerProducerServices.put(brokerProducerService.getName(), brokerProducerService);
	}

	@Override
	public BrokerProducerService<V> getBrokerProducerService(String brokerProducerServiceName) {
		return brokerProducerServices.get(brokerProducerServiceName);
	}

	@Override
	public String toString() {
		return "BrokerProducerServiceFactoryImpl [brokerProducerServices=" + brokerProducerServices + "]";
	}

}
