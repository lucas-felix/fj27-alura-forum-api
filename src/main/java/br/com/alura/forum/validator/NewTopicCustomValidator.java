package br.com.alura.forum.validator;

import br.com.alura.forum.controller.dto.input.NewTopicInputDto;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.model.User;
import br.com.alura.forum.repository.TopicRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class NewTopicCustomValidator implements Validator {

    private final TopicRepository topicRepository;
    private User loggedUser;

    public NewTopicCustomValidator(TopicRepository topicRepository, User loggedUser) {
        this.topicRepository = topicRepository;
        this.loggedUser = loggedUser;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return NewTopicInputDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Instant oneHourAgo = Instant.now().minus(1, ChronoUnit.HOURS);
        List<Topic> topics = topicRepository
                .findByOwnerAndCreationInstantAfterOrderByCreationInstantAsc(loggedUser, oneHourAgo);

        if (topics.size() >= 4) {
            
            Instant instantOfTheOldestTopic = topics.get(0).getCreationInstant();
            long minutesToNextTopic = Duration.between(oneHourAgo, instantOfTheOldestTopic)
                    .getSeconds() / 60;

            errors.reject("newTopicInputDto.limit.exceeded",
                    new Object[] {minutesToNextTopic},
                    "O limite individual de novos tópicos por hora foi excedido");
        }
    }
}