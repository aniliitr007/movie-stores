package com.akgcloud.moviekafkaconsumer.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.akgcloud.moviekafkaconsumer.model.MovieCatalog;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	@Bean
	public ConsumerFactory<String, MovieCatalog> consumerFactory() {
		Map<String, Object> config = new HashMap<>();
		config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
//		config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//		config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

//		final JsonDeserializer<MovieCatalog> valueDeserializer = new JsonDeserializer<>();
//		valueDeserializer.addTrustedPackages("com.akgcloud");
//		valueDeserializer.

//		config.put("spring.kafka.consumer.properties.spring.json.trusted.packages", "*");
		return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
				new JsonDeserializer<>(MovieCatalog.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, MovieCatalog> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, MovieCatalog> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
