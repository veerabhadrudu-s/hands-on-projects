package com.handson.broker.service.kafka.test;

import org.junit.Before;
import org.junit.Test;

import com.handson.broker.service.consumer.kafka.KafkaConsumerServiceTest;
import com.handson.broker.service.producer.kafka.KafkaProducerServiceTest;

/**
 * @author sveera
 *
 */
public class KafkaProducerConsumerServiceTest {
	private KafkaProducerServiceTest kafkaProducerServiceTest;
	private KafkaConsumerServiceTest kafkaConsumerServiceTest;

	@Before
	public void setUp() throws Exception {
		kafkaProducerServiceTest = new KafkaProducerServiceTest();
		kafkaConsumerServiceTest = new KafkaConsumerServiceTest();
	}

	@Test
	public void testKafkaProducerConsumerServicesWithDefaultSettings() throws InterruptedException {
		kafkaProducerServiceTest.testPublishDataWithDefaultSerializers();
		kafkaConsumerServiceTest.testPublishDataWithDefaultDeSerializer();
	}

	@Test
	public void testKafkaProducerConsumerServicesWithCustomSettings() throws InterruptedException {
		kafkaProducerServiceTest.testPublishDataWithExternalSerializers();
		kafkaConsumerServiceTest.testConsumeDataWithExternalDeSerializer();
	}

	@Test
	public void testKafkaProducerConsumerServicesUsingConsumerGroup() throws InterruptedException {
		kafkaProducerServiceTest.testPublishDataWithExternalSerializers();
		kafkaConsumerServiceTest.testConsumeDataWithConsumerGroup();
	}

}
