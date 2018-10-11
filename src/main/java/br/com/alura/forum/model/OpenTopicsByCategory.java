package br.com.alura.forum.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;
import java.util.Date;

@Entity
public class OpenTopicsByCategory {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String categoryName;
    private int topicCount;
    private Instant instant;

    @Deprecated
    public OpenTopicsByCategory() {
    }

    public OpenTopicsByCategory(String categoryName, Number topicCount, Date instant) {
        this.categoryName = categoryName;
        this.topicCount = topicCount.intValue();
        this.instant = Instant.ofEpochMilli(instant.getTime());
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getTopicCount() {
        return topicCount;
    }

    public Instant getInstant() {
        return instant;
    }
}
