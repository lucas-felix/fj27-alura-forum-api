package br.com.alura.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.repository.TopicRepository;

@CrossOrigin
@RestController
public class TopicController {

	@Autowired
	private TopicRepository topicRepository;

	@GetMapping(value = "/api/topics", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TopicBriefOutputDto> listTopics() {
		
		List<Topic> topics =  topicRepository.findAll();
		return TopicBriefOutputDto.listFromTopics(topics);
	}
}
