package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.model.Category;
import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.model.User;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin
@RestController
@RequestMapping("/api/topics")
class TopicController {

	@GetMapping
	ResponseEntity<List<TopicBriefOutputDto>> listTopics() {
		var topics = createEntityTopics();
		var topicsResponse = TopicBriefOutputDto.fromTopics(topics);

		return ok(topicsResponse);
	}

	private List<Topic> createEntityTopics() {
		var subcategory = new Category("Java", new Category("Programação"));
		var course = new Course("Java e JSF", subcategory);
		var topic = new Topic("Problemas com o JSF", new User("Fulano"), course);
		return Arrays.asList(topic, topic, topic);
	}
}
