package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.input.NewAnswerInputDto;
import br.com.alura.forum.controller.dto.output.AnswerOutputDto;
import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.topic.domain.Topic;
import br.com.alura.forum.model.User;
import br.com.alura.forum.repository.AnswerRepository;
import br.com.alura.forum.repository.TopicRepository;
import br.com.alura.forum.service.NewReplyProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@Controller
@RequestMapping("/api/topics/{topicId}/answers")
public class AnswerController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private NewReplyProcessorService newReplyProcessorService;

    @Autowired
    private AnswerRepository answerRepository;

    @CacheEvict(value = "topicDetails", key = "#topicId")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnswerOutputDto> answerTopic(@PathVariable Long topicId,
            @Valid @RequestBody NewAnswerInputDto newAnswerDto,
            @AuthenticationPrincipal User loggedUser,
            UriComponentsBuilder uriBuilder) {

        Topic topic = this.topicRepository.findById(topicId);
        Answer answer = newAnswerDto.build(topic, loggedUser);

        this.newReplyProcessorService.record(answer);

        URI path = uriBuilder
                .path("/api/topics/{topicId}/answers/{answerId}")
                .buildAndExpand(topicId, answer.getId())
                .toUri();

        return ResponseEntity.created(path).body(new AnswerOutputDto(answer));
    }

    @Transactional
    @CacheEvict(value = "topicDetails", key = "#topicId")
    @PostMapping("/{answerId}/solution")
    @PreAuthorize("hasPermission(#topicId, 'Topic', 'ADMINISTRATION')")
    public ResponseEntity<?> markAsSolution(@PathVariable Long topicId,
            @PathVariable Long answerId, UriComponentsBuilder uriBuilder,
            @AuthenticationPrincipal User loggedUser) {

        Answer answer = this.answerRepository.findById(answerId);
        answer.markAsSolution();

        URI path = uriBuilder
                .path("/api/topics/{topicId}/solution")
                .buildAndExpand(topicId)
                .toUri();

        return ResponseEntity.created(path)
                .body(new AnswerOutputDto(answer));
    }
}
