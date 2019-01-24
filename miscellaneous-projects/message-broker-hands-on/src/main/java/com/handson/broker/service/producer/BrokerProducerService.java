package com.handson.broker.service.producer;

import com.handson.broker.service.BrokerConnectorService;

/**
 * @author sveera
 *
 */
public interface BrokerProducerService<V> extends BrokerConnectorService {

	void publishData(String destinationName, V value);
}
