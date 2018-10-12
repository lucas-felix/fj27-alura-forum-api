package br.com.alura.forum.repository;

import br.com.alura.forum.model.User;
import br.com.alura.forum.model.topic_domain.Topic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TopicRepositoryTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private TopicRepository topicRepository;

    @Test
    public void shouldSaveATopic() {

        User user = new User("Usuário", "usuario@email.com", "123456");
        Topic topic = new Topic("Descrição do tópico", "Conteúdo do tópico", user, null);

        Topic createdTopic = this.topicRepository.save(topic);
        Topic foundTopic = this.testEntityManager.find(Topic.class, createdTopic.getId());

        assertThat(createdTopic).isNotNull();

        assertThat(createdTopic.getShortDescription())
                .isEqualTo(foundTopic.getShortDescription());

        assertThat(createdTopic.getOwnerName())
                .isEqualTo(foundTopic.getOwnerName());
    }

}
