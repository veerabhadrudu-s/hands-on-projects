package com.handson.broker.service.consumer;

import com.handson.broker.service.BrokerConnectorService;
import com.handson.broker.service.consumer.handler.BrokerConsumerDataHandler;

/**
 * @author sveera
 *
 */
public interface BrokerConsumerService<V> extends BrokerConnectorService {

	void consumeData(String destination, BrokerConsumerDataHandler<V> brokerConsumerDataHandler);

}
