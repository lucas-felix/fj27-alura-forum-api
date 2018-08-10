package br.com.alura.forum.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.alura.forum.model.Category;
import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.Subcategory;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.model.User;

@Controller
@CrossOrigin
public class TopicController {

	@ResponseBody
	@RequestMapping("/api/topics")
	public List<Topic> listTopics() {
	
		Course course = new Course("Java parte 1", new Subcategory("Java", new Category("Programação")));
		Topic topic = new Topic("Problemas com o JSF", new User("Fulano"), course);
		
		return Arrays.asList(topic, topic, topic);
	}
}
