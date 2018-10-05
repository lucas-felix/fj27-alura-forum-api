package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.input.NewAnswerInputDto;
import br.com.alura.forum.controller.dto.output.AnswerOutputDto;
import br.com.alura.forum.infra.EmailSender;
import br.com.alura.forum.model.Answer;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.model.User;
import br.com.alura.forum.repository.AnswerRepository;
import br.com.alura.forum.repository.TopicRepository;
import br.com.alura.forum.service.NewReplyMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Controller
@RequestMapping("/api/topics/{topicId}/answers")
public class AnswerController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private NewReplyMailService newReplyMailService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AnswerOutputDto> answerTopic(@PathVariable Long topicId,
            @Valid @RequestBody NewAnswerInputDto newAnswerDto,
            @AuthenticationPrincipal User loggedUser,
            UriComponentsBuilder uriBuilder) {

        Topic topic = this.topicRepository.findById(topicId);
        Answer answer = newAnswerDto.build(topic, loggedUser);

        this.answerRepository.save(answer);

        this.newReplyMailService.prepareAndSend(topic, answer);

        URI path = uriBuilder
                .path("/api/topics/{topicId}/answers/{answer}")
                .buildAndExpand(topicId, answer.getId())
                .toUri();

        return ResponseEntity.created(path).body(new AnswerOutputDto(answer));
    }
}