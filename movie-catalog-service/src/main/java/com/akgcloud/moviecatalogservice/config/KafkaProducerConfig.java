package com.akgcloud.moviecatalogservice.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.akgcloud.moviecatalogservice.model.MovieCatalog;

@Configuration
public class KafkaProducerConfig {

	/*
	 * create kafka topic using cmd line bin\windows> kafka-topics.bat --create
	 * --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic
	 * movie_catalog
	 */

	@Bean
	public ProducerFactory<String, MovieCatalog> producerFactory() {
		Map<String, Object> configs = new HashMap<String, Object>();
		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		final JsonSerializer<MovieCatalog> valueSerializer = new JsonSerializer<>();
		valueSerializer.setAddTypeInfo(false);
		return new DefaultKafkaProducerFactory<>(configs, new StringSerializer(), valueSerializer);
	}

	@Bean
	public KafkaTemplate<String, MovieCatalog> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	/*
	 * check kafka consumer bin\windows> kafka-console-consumer.bat
	 * --bootstrap-server localhost:9092 --topic movie_catalog
	 */

}
