/**
 * 
 */
package com.handson.broker.service.consumer.factory;

import com.handson.broker.service.consumer.BrokerConsumerService;

/**
 * @author sveera
 *
 */
public interface BrokerConsumerServiceFactory<V> {

	BrokerConsumerService<V> getBrokerConsumerService(String brokerConsumerServiceName);
}
