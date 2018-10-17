package br.com.alura.forum.actuator.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import br.com.alura.forum.model.OpenTopicsByCategory;
import br.com.alura.forum.repository.TopicRepository;

@Endpoint(id="open-topics-by-category")
@Component
public class UnansweredTopicsActuatorEndpoint {
	
	@Autowired
	private TopicRepository topicRepository;
	
	@ReadOperation
	@Bean
	public List<OpenTopicsByCategory> execute() {
		return topicRepository.findOpenTopicsByCategory();
	}
}
