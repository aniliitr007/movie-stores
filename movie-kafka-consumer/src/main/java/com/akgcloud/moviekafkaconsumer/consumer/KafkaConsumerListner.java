package com.akgcloud.moviekafkaconsumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.akgcloud.moviekafkaconsumer.model.MovieCatalog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumerListner {

	@KafkaListener(topics = "movie_catalog3", groupId = "group_json", containerFactory = "kafkaListenerContainerFactory")
	public void consume(MovieCatalog catalog) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = null;
		try {
			jsonString = mapper.writeValueAsString(catalog);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		System.out.println("listening movie catalog kafka!!! " + jsonString);
	}
}
