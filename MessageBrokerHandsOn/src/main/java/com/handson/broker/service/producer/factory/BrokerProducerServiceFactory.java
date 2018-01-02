/**
 * 
 */
package com.handson.broker.service.producer.factory;

import com.handson.broker.service.producer.BrokerProducerService;

/**
 * @author sveera
 *
 */
public interface BrokerProducerServiceFactory<V> {

	BrokerProducerService<V> getBrokerProducerService(String brokerProducerServiceName);
}
