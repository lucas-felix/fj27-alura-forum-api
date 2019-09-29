package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.output.TopicBriefOutputDto;
import br.com.alura.forum.model.Category;
import br.com.alura.forum.model.Course;
import br.com.alura.forum.model.Topic;
import br.com.alura.forum.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.OK;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class TopicsTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void shouldReturnAllTopics() {

        var response = testRestTemplate.exchange("/api/topics", GET, null,
                                            new ParameterizedTypeReference<List<TopicBriefOutputDto>>() {});
        var responseBody = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(OK);
        assertThat(responseBody).containsExactlyInAnyOrder(createTopics());
    }

    private TopicBriefOutputDto[] createTopics() {
        var subcategory = new Category("Java", new Category("Programação"));
        var course = new Course("Java e JSF", subcategory);
        var topic = new Topic("Problemas com o JSF", new User("Fulano"), course);

        return TopicBriefOutputDto.fromTopics(topic, topic, topic).toArray(TopicBriefOutputDto[]::new);
    }
}
