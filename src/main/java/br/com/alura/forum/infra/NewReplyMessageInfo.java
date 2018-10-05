package br.com.alura.forum.infra;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class NewReplyMessageInfo {

    private String topicOwnerName;
    private String topicOwnerEmail;
    private String topicShortDescription;
    private String answerAuthor;
    private Instant answerCreationInstant;
    private String answerContent;

    public NewReplyMessageInfo(String topicOwnerName, String topicOwnerEmail,
            String topicShortDescription, String answerAuthor,
            Instant answerCreationTime, String answerContent) {

        this.topicOwnerName = topicOwnerName;
        this.topicOwnerEmail = topicOwnerEmail;
        this.topicShortDescription = topicShortDescription;
        this.answerAuthor = answerAuthor;
        this.answerCreationInstant = answerCreationTime;
        this.answerContent = answerContent;
    }

    public String getTopicOwnerName() {
        return topicOwnerName;
    }

    public String getTopicOwnerEmail() {
        return topicOwnerEmail;
    }

    public String getTopicShortDescription() {
        return topicShortDescription;
    }

    public String getAnswerAuthor() {
        return answerAuthor;
    }

    public String getAnswerCreationInstant() {

        return DateTimeFormatter.ofPattern("kk:mm")
                .withZone(ZoneId.of("America/Sao_Paulo"))
                .format(this.answerCreationInstant);
    }

    public String getAnswerContent() {
        return answerContent;
    }
}
