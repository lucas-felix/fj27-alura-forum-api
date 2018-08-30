package br.com.alura.forum.controller;

import java.util.List;
import java.util.Optional;

import br.com.alura.forum.controller.dto.input.TopicSearchInputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controller.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.model.TopicStatus;
import br.com.alura.forum.repository.TopicRepository;


@CrossOrigin
@RestController
public class TopicController {

	@Autowired
	private TopicRepository topicRepository;


    @GetMapping(value = "/api/topics", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TopicBriefOutputDto> list(TopicSearchInputDto topicSearch) {

        Specification<Topic> topicSpecification = topicSearch.build();
        List<Topic> topics = this.topicRepository.findAll(topicSpecification);

        return TopicBriefOutputDto.listFromTopics(topics);
    }
}
