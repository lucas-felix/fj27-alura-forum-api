package br.com.alura.forum.model;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class TopicSpammer {

    private List<Topic> topics;

    public TopicSpammer(List<Topic> topics) {
        this.topics = topics;
    }

    public boolean hasExceededLimit() {
        return this.topics.size() >= 4;
    }

    public long minutesToNextTopic() {
        Instant oneHourAgo = Instant.now().minus(1, ChronoUnit.HOURS);
        Instant instantOfTheOldestTopic = topics.get(0).getCreationInstant();

        return Duration.between(oneHourAgo, instantOfTheOldestTopic)
                .getSeconds() / 60;
    }
}
